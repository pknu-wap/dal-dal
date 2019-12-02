package com.example.dalyeodalyeok;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;





public class ScheduleActivity extends AppCompatActivity {


    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod_date;
    private TextView textView_Time;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time;
    private DbOpenHelper mDbOpenHelper;
    String selDate;
    String selTime;
    String selSchedule;


    public ScheduleActivity() {
    }

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
    SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
    SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");
    String strCurYear= CurYearFormat.format(date);
    String strCurMonth = CurMonthFormat.format(date);
    String strCurDay = CurDayFormat.format(date);
    String strCurHour = CurHourFormat.format(date);
    String strCurMinute = CurMinuteFormat.format(date);

    int numYear = Integer.parseInt(strCurYear);
    int numMonth = Integer.parseInt(strCurMonth);
    int numDay = Integer.parseInt(strCurDay);
    int numHour = Integer.parseInt(strCurHour);
    int numMinute = Integer.parseInt(strCurMinute);
    public static final int sub = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    final TextView textView=(TextView)findViewById(R.id.spinner_text);
        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("반복:"+parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        this.InitializeView_date();
        this.InitializeListener_date();
        this.InitializeView_time();
        this.InitializeListener_time();

        textView_Time = (TextView)findViewById(R.id.textView_time);

        mDbOpenHelper = new DbOpenHelper(getApplicationContext());
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        Button finishBtn = findViewById(R.id.complete_button);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbOpenHelper.insertSchedule(selDate, selTime, selSchedule);
                showSchedule();

                finish();
            }
        });

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
                //int monthOfYear_update = monthOfYear + 1;
                selDate = Integer.toString(year) + "/" + Integer.toString(monthOfYear) + "/" + Integer.toString(dayOfMonth);
                Log.d("선택된 날짜", selDate);
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler_date(View view)
    {
        int numMonth_update=numMonth-1;
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod_date,numYear,numMonth_update,numDay  );

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

                selTime = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
                textView_Time.setText(hourOfDay + "시" + minute + "분");
                Log.d("선택된 시간", selTime);
            }
        };
    }
    public void OnClickHandler_time(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod_time, numHour, numMinute, true);

        dialog.show();
    }

    public void btn_Click(View view)
    {
        TextView textView = (TextView)findViewById(R.id.textView2);
        EditText editText = (EditText)findViewById(R.id.schedule);

        selSchedule = editText.getText().toString();
        textView.setText(editText.getText());
    }

    public void showSchedule() {
        Cursor iCursor = mDbOpenHelper.sortSchedule("_id");
        String result = "";
        while (iCursor.moveToNext()) {
            String tempDate = iCursor.getString(iCursor.getColumnIndex("date"));
            String tempTime = iCursor.getString(iCursor.getColumnIndex("time"));
            String tempSchedule = iCursor.getString(iCursor.getColumnIndex("schedule"));

            result += tempDate + "$" + tempTime + "$" + tempSchedule + "\n";
        }

        Log.d("스케줄", result);
    }

}
