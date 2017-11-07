package com.example.ashwin.sqlitetry1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tname, tpass;
    EditText ename, epass;
    Button badd, bview;
    MyAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tname = (TextView) findViewById(R.id.textviewname);
        tpass = (TextView) findViewById(R.id.textviewpass);
        ename = (EditText) findViewById(R.id.edittextname);
        epass = (EditText) findViewById(R.id.edittextpass);
        badd = (Button) findViewById(R.id.buttonadd);
        bview = (Button) findViewById(R.id.buttonview);
        helper = new MyAdapter(this);
    }

    public void add(View view)
    {
        String t1 = ename.getText().toString();
        String t2 = epass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else  {
            long id = helper.insertData(t1,t2);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                ename.setText("");
                epass.setText("");
            }
            else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                ename.setText("");
                epass.setText("");
            }
    }
    }

    public void view(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }
}
