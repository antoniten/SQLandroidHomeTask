package ru.pavlenty.sqlite_example;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ViewPersons extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextAdd;
    private Button btnShow;
    private DatabaseHelper db;
    private TextView TextView1;
    private TextView TextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_persons);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAdd = (EditText) findViewById(R.id.editTextAddress);

        btnShow = (Button) findViewById(R.id.btnShow);

        db = new DatabaseHelper(this);


        TextView1 = (TextView) findViewById(R.id.text11);
        TextView2 = (TextView) findViewById(R.id.text22);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPerson(Integer.parseInt(editTextId.getText().toString().trim()));
            }
        });
    }

    private void showPerson(int id) {
        try {
            while (true) {

                Cursor c = db.getPerson(id);
                c.moveToLast();
                if (c != null) {
                    TextView1.setText(TextView1.getText().toString() + c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
                    TextView2.setText(TextView2.getText().toString() + c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_ADD)));
                }
                id++;
            }
        }
    catch (Exception e) {
    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}