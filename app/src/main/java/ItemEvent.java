import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parm.eventmaker.R;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class ItemEvent extends AppCompatActivity{

    TextView tvFullNote;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemevent);

        tvFullNote = findViewById(R.id.eventItemTV);

        /**
         * This activity is only opened through the Notes activity.
         * Gets the String that the Notes activity sent and displays it.
         */
        if(getIntent().getExtras().containsKey("note"))
        {
            String note = getIntent().getStringExtra("note");
            tvFullNote.setText(note);
        }
    }
}
