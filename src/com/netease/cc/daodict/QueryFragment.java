package com.netease.cc.daodict;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueryFragment extends Fragment{

	private Button btnQueryButton;
	private EditText editWord;
	private YoudaoDict dictDB;
	private TextView tVoice;
	private TextView tMeaning;
	private Typeface mFace;
	private String sLastVoice;
	private String sLastMeaning;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		Log.i("lifecircle", "onCreate");
		super.onCreate(savedInstanceState);
		sLastVoice = "";
		sLastMeaning = "";
	}
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		Log.i("lifecircle", "onCreateView");
		View view = inflater.inflate(R.layout.query_fragment, container, false);
		editWord = (EditText)view.findViewById(R.id.editText1);
		tVoice = (TextView)view.findViewById(R.id.voice);
		tMeaning = (TextView)view.findViewById(R.id.meaning);
		mFace = Typeface.createFromAsset(getResources().getAssets(), "fonts/segoeui.ttf");
				
		btnQueryButton = (Button)view.findViewById(R.id.query);
		btnQueryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//editWord.setText("hihihihih");

		        String text = editWord.getText().toString().trim();
		        if (text.isEmpty()) {
		            //showTranslatedContent(false);
		           // transLateTip.setText(getString(R.string.input_empty_tip));
		        } else {
		            String[] transList = new String[2];
		            Log.d("inputinfo",text.toLowerCase());
		            transList = dictDB.get(text.toLowerCase());
		            if (transList == null || transList[1].isEmpty()) {
		               // showTranslatedContent(false);
		               // tVoice.setText(getString(R.string.translated_error));
		                Log.e("translateError","no result");
		            } else {
		                //showTranslatedContent(true);
		            	sLastVoice = transList[0];
		            	sLastMeaning = transList[1];
		                tVoice.setText(sLastVoice);
		                tMeaning.setText(sLastMeaning);
		        		tVoice.setTypeface(mFace);
		        		tMeaning.setTypeface(mFace);
		                Log.d("translateInfo", transList.toString() + transList[0] + transList[1]);
		            }
		        }
			}
		});
		

        AsyncLoadDict task = new AsyncLoadDict();
        task.execute();
        
        return view;
    }

    class AsyncLoadDict extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {
            loadDictFile();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            btnQueryButton.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
    private void loadDictFile() {
        try {
            InputStream inputReader = getResources().openRawResource(R.raw.dictcn);
            dictDB = new YoudaoDict(inputReader);
            inputReader.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.e("readfile", "read dict file error");
        }
    }
    
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("lifecircle", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i("lifecircle", "onPause");
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i("lifecircle", "onResume");
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.i("lifecircle", "onStart");
		super.onStart();

        tVoice.setText(sLastVoice);
        tMeaning.setText(sLastMeaning);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.i("lifecircle", "onStop");
		super.onStop();
	}

}
