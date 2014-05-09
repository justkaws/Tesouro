package com.meutesouro.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class MoneyTitleService extends Service {

	public static String TAG = MoneyTitleService.class.getSimpleName();
	
	private Handler handler;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				try {
				    // preparing a looper on current thread     
				    // the current thread is being detected implicitly
				    Looper.prepare();
				 
				    // now, the handler will automatically bind to the
				    // Looper that is attached to the current thread
				    // You don't need to specify the Looper explicitly
				    handler = new Handler();
				     
				    // After the following line the thread will start
				    // running the message loop and will not normally
				    // exit the loop unless a problem happens or you
				    // quit() the looper (see below)
				    Looper.loop();
				    
				    while(true)
				    {
				    	
				    	
				    	Thread.sleep(60000);
				    }				    
				
				  } catch (Throwable t) {
				    Log.e(TAG, "halted due to an error", t);
				  } 
			}
		};
	}

}
