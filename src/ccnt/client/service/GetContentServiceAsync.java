package ccnt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetContentServiceAsync {
	public void getRemote(String url, AsyncCallback callback);
	public void getOtherService(String concept,  String type, AsyncCallback callback);
}
