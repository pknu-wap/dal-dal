package com.example.dalyeodalyeok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dalyeodalyeok.ui.home.HomeFragment;

public class ScheduleActivity extends AppCompatActivity {

    //int year;
    //int month;
    //int day;

    String strY;
    String strM;
    String strD;

    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod_date;
    private TextView textView_Time;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time;

    public ScheduleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        this.InitializeView_date();
        this.InitializeListener_date();
        this.InitializeView_time();
        this.InitializeListener_time();

        textView_Time = (TextView)findViewById(R.id.textView_time);

//        year = getIntent().getIntExtra("year", 2000);
//        month = getIntent().getIntExtra("month", 8);
//        day = getIntent().getIntExtra("day", 4);

//        year = getIntent().getIntExtra("strY", 2000);
//        month = getIntent().getIntExtra("strM", 8);
//        day = getIntent().getIntExtra("strD", 4);

       // strY = getIntent().getStringExtra("strY");
        //strM = getIntent().getStringExtra("strM");
        //strD = getIntent().getStringExtra("strD");


       // Toast.makeText(getApplicationContext(), year+"/"+month+"/"+day, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), strY+"/"+strM+"/"+strD, Toast.LENGTH_LONG).show();

        Button OK = (Button)findViewById(R.id.OK);
        OK.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(view.getId() == R.id.OK) {
                    btn_Click(view);
                }
            }
        });
    }
    public void InitializeView_date()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener_date()
    {
        callbackMethod_date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler_date(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod_date, 2019, 5, 24);

        dialog.show();
    }
    public void InitializeView_time()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener_time()
    {
        callbackMethod_time = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                textView_Time.setText(hourOfDay + "시" + minute + "분");
            }
        };
    }
    public void OnClickHandler_time(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod_time, 8, 10, true);

        dialog.show();
    }


    public void btn_Click(View view)
    {
        TextView textView = (TextView)findViewById(R.id.textView2);
        EditText editText = (EditText)findViewById(R.id.schedule);


        textView.setText(editText.getText());
    }
}
