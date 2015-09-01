package com.techscl.lovechat.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.utils.To;
import com.techscl.lovechat.zxing.activity.CaptureActivity;
import com.techscl.lovechat.zxing.encoding.EncodingHandler;

/**
 * Created by 宋春麟 on 15/9/1.
 */
public class CodeScanActivity extends GestureActivity {
    private LinearLayout scanner_code_layout;
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private Toolbar toolbar;
    private Button scanBarCodeButton, generateQRCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scan);

        initView();

        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * 打开扫描
             * @param v
             */
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(CodeScanActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * 生成二维码
             * @param v
             */
            @Override
            public void onClick(View v) {
                try {
                    String contentString = qrStrEditText.getText().toString();
                    if (!contentString.equals("")) {
                        //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                        Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
                        qrImgImageView.setImageBitmap(qrCodeBitmap);
                    } else {
                        To.showShort(CodeScanActivity.this, "请输入内容");
                    }

                } catch (WriterException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        scanner_code_layout = (LinearLayout) this.findViewById(R.id.scanner_code_layout);
        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
        scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        toolbar = (Toolbar) this.findViewById(R.id.scanner_code_toolbar);
        toolbar.setTitle(R.string.scanner_code);
        scanner_code_layout.setOnTouchListener(this);
    }

    /**
     * 接收扫描结果
     *
     * @param requestCode 请求吗
     * @param resultCode  结果码
     * @param data        数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            resultTextView.setText(scanResult);
        }
    }
}
