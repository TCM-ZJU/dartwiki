package ccnt.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GetContentService extends RemoteService {
	public String getRemote(String url);
	public String getOtherService(String concept, String type);
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static GetContentServiceAsync instance;
		public static GetContentServiceAsync getInstance(){
			if (instance == null) {
				instance = (GetContentServiceAsync) GWT.create(GetContentService.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				System.out.println(GWT.getModuleBaseURL() + "GetContent.rpc");
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "GetContent.rpc");
			}
			return instance;
		}
	}
}