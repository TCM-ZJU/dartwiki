package ccnt.client.service;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;

public abstract class AsyncCallbackWithMask  implements AsyncCallback {

  public AsyncCallbackWithMask(String message) {   
      setLoading(message);   
  }   

  public final void onSuccess(Object object) {   
      resetLoading();
      onSuccessInternal(object);
  }   

  public final void onFailure(Throwable throwable) {   
      resetLoading();
      onFailureInternal(throwable);
  }   

  protected abstract void onSuccessInternal(Object object);   

  protected void onFailureInternal(Throwable throwable){   
      Window.alert("服务器没有响应,请稍后再试!!!");   
      Window.alert(throwable.toString());
  }   

  private void setLoading(String message) {
    final ExtElement element = Ext.get("show-panel");
    if (message.trim().equals("")) {
    	element.mask();
    } else {
    	element.mask("正在" + message.trim() + "，请稍候");   
    }
  }
  
  private void resetLoading(){
  	Ext.get("show-panel").unmask();
  }
}
