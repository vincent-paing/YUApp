package com.vapp.yangonuniversity;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportFragment extends Fragment {

	private EditText txt_subject;
	private EditText txt_content;
	private Context context;
	private Button send;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_report, container, false);
		context = rootView.getContext();
		txt_subject = (EditText) rootView.findViewById(R.id.ReportSubjectEditText);
		txt_content = (EditText) rootView.findViewById(R.id.ReportContent);
		send = (Button) rootView.findViewById(R.id.ReportSend);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				String subject = txt_subject.getText().toString();
				String content = txt_content.getText().toString();
				if (subject.isEmpty() || content.isEmpty()) {
					Toast.makeText(context, "Please Fill at both fields", Toast.LENGTH_LONG).show();
				} else {
					sendMail(subject, content);
				}
			}
		});
		return rootView;
	}
	
	private void sendMail(String subject, String content) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"uriaungkyawpaing@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, content);
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		
	}
}
