package com.achmas.pusher;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ThreadFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.ThreadManager;

@SuppressWarnings("serial")
public class GcmPusherServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.sendRedirect("gcmpusher.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		ThreadFactory factory = ThreadManager.currentRequestThreadFactory();
		factory.newThread(new SendPushTask(req, resp)).run();
	}

	private static class SendPushTask implements Runnable {

		private final String mRegistrationId;
		private final String mCollapseKey;
		private final String mMessage;
		private final String mAPIKey;
		private int mCounter;
		private final HttpServletResponse mResponse;

		public SendPushTask(HttpServletRequest request,
				HttpServletResponse response) {
			mAPIKey = request.getParameter("apiKey");
			mRegistrationId = request.getParameter("regId");
			mResponse = response;
			mCollapseKey = request.getParameter("collapseKey");
			mMessage = request.getParameter("message");
			mCounter = Integer.valueOf(request.getParameter("pushCount"));
		}

		@Override
		public void run() {
			PrintWriter writer = null;
			try {
				writer = mResponse.getWriter();
				for (int i = 0; i < mCounter; i++) {
					PrintResponse response = sendPush();
					writer.println("GCM HTTP status: " + response.mHttpStatus);
					writer.println("GCM response body: " + response.mBody);
					writer.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private PrintResponse sendPush() {
			HttpURLConnection con = null;
			try {
				URL url = new URL("https://android.googleapis.com/gcm/send");
				con = (HttpURLConnection) url.openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", "key=" + mAPIKey);

				// send post request
				String postParams = buildParams();
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(
						con.getOutputStream());
				wr.writeBytes(postParams);
				wr.flush();
				wr.close();

				int responseCode = con.getResponseCode();

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder(/*"Gcm response code:"
						+ responseCode + '\n'*/);

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				String resp = response.toString();
				return new PrintResponse(responseCode, resp);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
			return null;
		}

		private String buildParams() {
			StringBuilder params = new StringBuilder("registration_id="
					+ mRegistrationId);
			if (!isStringEmpty(mCollapseKey)) {
				params.append("&collapse_key=").append(mCollapseKey);
			}
			if (!isStringEmpty(mMessage)) {
				params.append("&data.message=").append(mMessage);
			}
			return params.toString();
		}

		private static boolean isStringEmpty(String str) {
			return str == null || str.length() == 0;
		}
		
		private class PrintResponse {
			private final int mHttpStatus;
			private final String mBody;

			PrintResponse(int httpStatus, String body) {
				mHttpStatus = httpStatus;
				mBody = body;
			}
		}

	}
	

}
