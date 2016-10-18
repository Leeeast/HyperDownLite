package xyz.geminiwen.hyperdown.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import xyz.geminiwen.hyperdown.HyperDown;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.tv_content);

        HyperDown hyperDown = new HyperDown("以下是块状代码\n" +
                "```java\n" +
                "public static void main(String[] args) {\n" +
                " System.out.println(\"产品\");\n" +
                " System.out.println(\"到底在想些什么\");\n" +
                "}\n" +
                "\n" +
                "```\n" +
                "\n" +
                "以下是引用\n" +
                "> 什么鬼\n" +
                "\n" +
                "以下是列表\n" +
                "\n" +
                "1. Hello\n" +
                "2. World\n" +
                "\n" +
                "然后接下去是行内元素\n" +
                "**Delectus** hic _ut_ eos sunt laborum `harum` ducimus. Quasi neque adipisci voluptatem consequatur aut. Et eos ut harum. Quis ut ut veritatis rem qui. [这是一个链接](https://google.com.hk)");
        CharSequence text = hyperDown.parse();

        textView.setMovementMethod(new LinkMovementMethod());
        textView.setText(text);
    }
}
