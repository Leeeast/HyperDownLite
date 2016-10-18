package xyz.geminiwen.hyperdown;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geminiwen on 17/10/2016.
 */

public class HyperDown {

    private static final Pattern sCodePattern = Pattern.compile("(^|[^\\\\])(`+)([^`]+?)\\2");
    private static final Pattern sBoldPattern = Pattern.compile("(\\*{2})(.+?)\\1");
    private static final Pattern sItalicPattern = Pattern.compile("(_)(.+?)\\1");
    private static final Pattern sLinkPattern = Pattern.compile("\\[((?:[^\\]]|\\\\\\]|\\\\\\[)+?)\\]\\(((?:[^\\)]|\\\\\\)|\\\\\\()+?)\\)");

    private String mSource;
    private Object[] mCachedSpans;

    public HyperDown(String source) {
        this.mSource = source;
    }

    public CharSequence parse() {
        return parse(this.mSource);
    }

    private Spannable parse(CharSequence source) {
        SpannableStringBuilder sb = new SpannableStringBuilder(source);
        // code
        Matcher matcher = sCodePattern.matcher(sb);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            int start = result.start();
            int end = result.end();
            beginCacheSpans(sb, start, end);

            sb.delete(start, end);
            sb.insert(start, resetCacheSpans(code(parse(result.group(3)))));
            matcher.reset(sb);
        }

        // bold
        matcher = sBoldPattern.matcher(sb);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            int start = result.start();
            int end = result.end();
            beginCacheSpans(sb, start, end);

            sb.delete(start, end);
            sb.insert(start, resetCacheSpans(bold(parse(result.group(2)))));
            matcher.reset(sb);
        }

        // italic
        matcher = sItalicPattern.matcher(sb);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            int start = result.start();
            int end = result.end();
            beginCacheSpans(sb, start, end);

            sb.delete(start, end);
            sb.insert(start, resetCacheSpans(italic(parse(result.group(2)))));
            matcher.reset(sb);
        }

        matcher = sLinkPattern.matcher(sb);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            int start = result.start();
            int end = result.end();
            beginCacheSpans(sb, start, end);

            sb.delete(start, end);
            sb.insert(start,
                    resetCacheSpans(link(parse(result.group(1)), result.group(2))));
            matcher.reset(sb);
        }

        return sb;
    }

    private void beginCacheSpans(Spannable s, int start, int end) {
        mCachedSpans = s.getSpans(start, end, Object.class);
    }

    private Spannable resetCacheSpans(Spannable s) {
        if (mCachedSpans == null) return s;

        for (Object o : mCachedSpans) {
            s.setSpan(o, 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    private Spannable bold(CharSequence source) {
        SpannableStringBuilder sb = new SpannableStringBuilder(source);
        sb.setSpan(new StyleSpan(Typeface.BOLD), 0, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    private Spannable italic(CharSequence source) {
        SpannableStringBuilder sb = new SpannableStringBuilder(source);
        sb.setSpan(new StyleSpan(Typeface.ITALIC), 0, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    private Spannable code(CharSequence source) {
        SpannableStringBuilder sb = new SpannableStringBuilder(source);
        sb.setSpan(new CodeSpan(Color.parseColor("#f9f2f4")),
                0,
                source.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    private Spannable link(CharSequence source, String link) {
        SpannableStringBuilder sb = new SpannableStringBuilder(source);
        sb.setSpan(new URLSpan(link),
                0,
                source.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

}
