package com.example.tinker.textphoto;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tinker.textphoto.utils.ImageUtils;
import com.example.tinker.textphoto.utils.ScreenUtils;
import com.example.tinker.textphoto.utils.Toast;
import com.example.tinker.textphoto.utils.Utils;
import com.sloop.fonts.FontsManager;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    //字体
    private TextView mTextStyle;
    private TextView mTextStyle1;
    private TextView mTextStyle2;
    private TextView mTextStyle3;
    private TextView mTextStyle4;
    //字号
    private SeekBar mTextSizeBar;
    //字体颜色
    private TextView mTextColor;
    private TextView mTextColor1;
    private TextView mTextColor2;
    private TextView mTextColor3;
    private EditText mTextColor4;
    //背景颜色
    private TextView mTextBackground;
    private TextView mTextBackground1;
    private TextView mTextBackground2;
    private TextView mTextBackground3;
    private EditText mTextBackground4;
    //图片
    private ImageView mImg;
    private TextView mCreateButton;
    private EditText mTextEditor;

    private int mTextSize1 = 18;
    private int mTextColorNu = Color.parseColor("#333333");
    private int mTextBgColorNu = Color.parseColor("#ffffff");
    private int mTextStyleIndex = -1;

    //    private String[] textStyleS={"FZQingCTJ_Xi.otf","fzssksjt.ttf","wdxgbxk.TTF"};
    private String[] textStyleS = {"lbkr.ttf", "fzssksjt.ttf", "zbqp.ttf"};
    private Bitmap mBitmap;

    private String filePath;
    private RadioGroup mPhotoStyleGroup;
    private int checkid = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextStyle1 = (TextView) findViewById(R.id.textStyle1);
        mTextStyle2 = (TextView) findViewById(R.id.textStyle2);
        mTextStyle3 = (TextView) findViewById(R.id.textStyle3);
        mTextStyle4 = (TextView) findViewById(R.id.textStyle4);

        mTextSizeBar = (SeekBar) findViewById(R.id.textSizeBar);

        mTextColor1 = (TextView) findViewById(R.id.textColor1);
        mTextColor2 = (TextView) findViewById(R.id.textColor2);
        mTextColor3 = (TextView) findViewById(R.id.textColor3);
        mTextColor4 = (EditText) findViewById(R.id.textColor4);

        mTextBackground1 = (TextView) findViewById(R.id.textBackground1);
        mTextBackground2 = (TextView) findViewById(R.id.textBackground2);
        mTextBackground3 = (TextView) findViewById(R.id.textBackground3);
        mTextBackground4 = (EditText) findViewById(R.id.textBackground4);

        mPhotoStyleGroup = (RadioGroup) findViewById(R.id.photo_style);
        mCreateButton = (TextView) findViewById(R.id.create_img);
        mTextEditor = (EditText) findViewById(R.id.text_editor);
        mImg = (ImageView) findViewById(R.id.img);

        mTextSizeBar.setOnSeekBarChangeListener(this);

        mCreateButton.setOnClickListener(this);
        mTextStyle1.setOnClickListener(this);
        mTextStyle2.setOnClickListener(this);
        mTextStyle3.setOnClickListener(this);
        mTextStyle4.setOnClickListener(this);
        mTextColor1.setOnClickListener(this);
        mTextColor2.setOnClickListener(this);
        mTextColor3.setOnClickListener(this);
        mTextBackground1.setOnClickListener(this);
        mTextBackground2.setOnClickListener(this);
        mTextBackground3.setOnClickListener(this);

        mImg.setOnLongClickListener(this);
        mPhotoStyleGroup.setOnCheckedChangeListener(this);

        Utils.init(this);
        addWatchListener();
        initTextStyle();

        AndPermission.with(this)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .start();


    }

    private void initTextStyle() {
        FontsManager.initFormAssets(this, textStyleS[0]);
        FontsManager.changeFonts(mTextStyle1);

        FontsManager.initFormAssets(this, textStyleS[1]);
        FontsManager.changeFonts(mTextStyle2);

        FontsManager.initFormAssets(this, textStyleS[2]);
        FontsManager.changeFonts(mTextStyle3);
    }

    private void addWatchListener() {
        mTextColor4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    try {
                        mTextColorNu = Color.parseColor(mTextColor4.getText().toString());
                        mTextEditor.setTextColor(mTextColorNu);
                        mTextColor4.setTextColor(mTextColorNu);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mTextBackground4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    try {

                        mTextBgColorNu = Color.parseColor(mTextBackground4.getText().toString());
                        mTextEditor.setBackgroundColor(mTextBgColorNu);
                        mTextBackground4.setBackgroundColor(mTextBgColorNu);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_img:
                Utils.hideSoftKeyBoard(this);
                createImage();

                break;
            case R.id.textStyle1:
                FontsManager.initFormAssets(this, textStyleS[0]);
                FontsManager.changeFonts(mTextStyle1);
                FontsManager.changeFonts(mTextEditor);
                mTextStyleIndex = 0;
                break;
            case R.id.textStyle2:
                FontsManager.initFormAssets(this, textStyleS[1]);
                FontsManager.changeFonts(mTextStyle2);
                FontsManager.changeFonts(mTextEditor);
                mTextStyleIndex = 1;
                break;
            case R.id.textStyle3:
                FontsManager.initFormAssets(this, textStyleS[2]);
                FontsManager.changeFonts(mTextStyle3);
                FontsManager.changeFonts(mTextEditor);
                mTextStyleIndex = 2;
                break;
            case R.id.textStyle4:
                Toast.show(MainActivity.this, "功能开发中...");
                break;
            case R.id.textColor1:
                mTextColorNu = Color.parseColor(mTextColor1.getText().toString());
                mTextEditor.setTextColor(mTextColorNu);
                break;
            case R.id.textColor2:
                mTextColorNu = Color.parseColor(mTextColor2.getText().toString());
                mTextEditor.setTextColor(mTextColorNu);
                break;
            case R.id.textColor3:
                mTextColorNu = Color.parseColor(mTextColor3.getText().toString());
                mTextEditor.setTextColor(mTextColorNu);
                break;
            case R.id.textBackground1:
                mTextBgColorNu = Color.parseColor(mTextBackground1.getText().toString());
                mTextEditor.setBackgroundColor(mTextBgColorNu);
                break;
            case R.id.textBackground2:
                mTextBgColorNu = Color.parseColor(mTextBackground2.getText().toString());
                mTextEditor.setBackgroundColor(mTextBgColorNu);
                break;
            case R.id.textBackground3:
                mTextBgColorNu = Color.parseColor(mTextBackground3.getText().toString());
                mTextEditor.setBackgroundColor(mTextBgColorNu);
                break;
        }
    }

    private void createImage() {
        mBitmap = textAsBitmap(mTextEditor.getText().toString());
        mImg.setImageBitmap(mBitmap);
    }

    public Bitmap textAsBitmap(String text) {
        float scale = this.getResources().getDisplayMetrics().scaledDensity;

        TextPaint textPaint = new TextPaint();

        // textPaint.setARGB(0x31, 0x31, 0x31, 0);
        textPaint.setColor(mTextColorNu);

        textPaint.setTextSize(mTextSize1 * scale);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(50);
        textPaint.setAntiAlias(true);//设置抗锯齿
        if (mTextStyleIndex >= 0) {
            //自定义字体库，放在main/assets目录下
            Typeface typeface = Typeface.createFromAsset(getAssets(), textStyleS[mTextStyleIndex]);
            textPaint.setTypeface(typeface);
        }

        StaticLayout staticLayout = new StaticLayout(text, textPaint,
                ((int) Math.ceil(StaticLayout.getDesiredWidth(text, textPaint)) <= 760 ?
                        (int) Math.ceil(StaticLayout.getDesiredWidth(text, textPaint)) : 760),
                Layout.Alignment.ALIGN_CENTER, 1.2f, 0.0f, false);

        Rect staticLayoutRect = new Rect(0, 0, staticLayout.getEllipsizedWidth(), staticLayout.getHeight());

        int width = (checkid == 1) ? (staticLayout.getEllipsizedWidth() + dip2px(6)) : ScreenUtils.getScreenWidth();
        int height = (checkid == 1) ? (staticLayout.getHeight() + dip2px(6)) : ScreenUtils.getScreenHeight();
        Bitmap bitmap = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);


        //此处中心点的设置  一大一小两个矩形  小矩形在大的中心位置
        int x = (bitmap.getWidth() - staticLayoutRect.width()) / 2;
        int y = (bitmap.getHeight() - staticLayoutRect.height()) / 2;

        canvas.translate(x, y);
        canvas.drawColor(mTextBgColorNu);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        staticLayout.draw(canvas);
        return bitmap;
    }

    public Bitmap createCodeBitmap(String contents) {
        float scale = this.getResources().getDisplayMetrics().scaledDensity;

        TextView tv = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);
        tv.setText(contents);
        tv.setTextSize(mTextSize1);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setDrawingCacheEnabled(true);
        tv.setTextColor(mTextColorNu);
        tv.measure(View.MeasureSpec.makeMeasureSpec(750, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.UNSPECIFIED));
        tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight());

        tv.setBackgroundColor((int) (scale * mTextBgColorNu));

        tv.buildDrawingCache();
        Bitmap bitmapCode = tv.getDrawingCache();
        return bitmapCode;
    }

    @Override
    public boolean onLongClick(View v) {
        createCrashFilePath();
        boolean yes = ImageUtils.save(mBitmap, filePath, Bitmap.CompressFormat.PNG);
        if (yes) {
            Toast.show(this, "图片已经保存到本地textPhoto目录下");
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextSize1 = progress;
        mTextEditor.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize1);

        if (TextUtils.isEmpty(mTextEditor.getText().toString().trim())) {
            SpannableString ss = new SpannableString(mTextEditor.getHint());
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(progress, true);
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextEditor.setHint(ss);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        float rs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getDisplayMetrics());
        return (int) rs;
    }

    public String createCrashFilePath() {

        String fileName = "text_photo_" + System.currentTimeMillis() + ".png";
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "textPhoto/";
                File dir = new File(path);
                if (!dir.exists()) dir.mkdirs();
                // 创建新的文件
                if (!dir.exists()) dir.createNewFile();

                filePath = path + fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.photo_style_phone) {
            checkid = 2;
        } else {
            checkid = 1;
        }
    }
}
