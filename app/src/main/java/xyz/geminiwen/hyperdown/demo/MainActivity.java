package xyz.geminiwen.hyperdown.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.widget.TextView;

import xyz.geminiwen.hyperdown.HyperDown;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.tv_content);

        HyperDown hyperDown = new HyperDown("`code` **strong** _italic_ **[link](http://www.baidu.com)**");
        CharSequence text = hyperDown.parse();

        textView.setText(text);
    }
}
