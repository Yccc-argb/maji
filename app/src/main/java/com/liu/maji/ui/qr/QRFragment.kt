package com.liu.maji.ui.qr

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.liu.maji.R
import com.liu.maji.base.MySupportFragment
import it.dongjun.mylibrary.activity.CaptureFragment
import it.dongjun.mylibrary.activity.CodeUtils
import kotlinx.android.synthetic.main.fragment_qrcode.*

class QRFragment : MySupportFragment<QRView, QRPresenter>(), View.OnClickListener {
    override fun onClick(v: View?) {
        _mActivity.onBackPressed()
    }

    private var captureFragment:CaptureFragment?=null


    override fun createPresenter(): QRPresenter {
        return QRPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qrcode,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        captureFragment = CaptureFragment()
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera)
        captureFragment!!.analyzeCallback = analyzeCallback
        _mActivity.supportFragmentManager.beginTransaction().replace(R.id.fl_my_container, captureFragment!!).commit()
        iv_close.setOnClickListener(this)
    }


    /**
     * 二维码解析回调函数
     */
    private var analyzeCallback: CodeUtils.AnalyzeCallback = object : CodeUtils.AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
//            toast("解析成功")
            println("result:$result")
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS)
            bundle.putString(CodeUtils.RESULT_STRING, result)
            resultIntent.putExtras(bundle)
            setFragmentResult(RESULT_OK,bundle)
            _mActivity.onBackPressed()
            //            SecondActivity.this.setResult(RESULT_OK, resultIntent);
            //            SecondActivity.this.finish();
        }

        override fun onAnalyzeFailed() {
            toast("解析失败")
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED)
            bundle.putString(CodeUtils.RESULT_STRING, "")
            resultIntent.putExtras(bundle)
            setFragmentResult(RESULT_OK,bundle)
            _mActivity.onBackPressed()
            //            SecondActivity.this.setResult(RESULT_OK, resultIntent);
            //            SecondActivity.this.finish();


        }
    }
}