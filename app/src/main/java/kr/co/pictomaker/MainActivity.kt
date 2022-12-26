package kr.co.pictomaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.pictomaker.common.application.CommonService

class MainActivity: AppCompatActivity() {
    private val commonService:CommonService = CommonService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        commonService.test("HI")
    }
}