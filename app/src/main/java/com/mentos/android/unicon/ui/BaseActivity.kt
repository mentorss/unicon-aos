package com.mentos.android.unicon.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.mentos.android.unicon.R
import com.mentos.android.unicon.utils.GlobalConstant


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    private var lottieDialog: LottieDialog? = null
    private var imm : InputMethodManager? = null

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun startNextActivity(activity: Class<*>?) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    open fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    open fun animateViewShake(view: View) {
        view.animate()
            .withEndAction {
                // here you can clear the fields after the shake
            }
            .xBy(-20f)
            .setInterpolator(GlobalConstant.decayingSineWave)
            .setDuration(300)
            .start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_none)
        lottieDialog = LottieDialog(this)

        initDataBinding()
        initAfterBinding()

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_out_right)
    }

//    fun showSallyDialog(
//        context: Context,
//        content: String,
//        btnText: String,
//        listener: SallyDialog.DialogClickListener
//    ) {
//        if (isFinishing) {
//            return
//        }
//        sallyDialog = SallyDialog(context, content, btnText)
//        sallyDialog?.setOnChangeListener(listener)
//        sallyDialog?.show()
//    }

    open fun showLottieDialog(context: Context) {
        if (isFinishing) {
            return
        }
        lottieDialog = LottieDialog(context)
        lottieDialog?.show()

        lottieDialog.let {
            if (lottieDialog!!.isShowing) {
//            ??????????????? ??? ??? ????????????
                lottieDialog!!.setCancelable(false);
                lottieDialog!!.show()
            }
        }
    }

    open fun hideLottieDialog() {
        lottieDialog.let {
            if (lottieDialog?.isShowing == true) {
                lottieDialog?.dismiss()
            }
        }
    }

    fun hideKeyboard(v: View){
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }


    fun vibrate(){
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        // 1??? ??????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                vibrator.vibrate(VibrationEffect.createOneShot(10, 100))
            };
        } else {
            vibrator.vibrate(1000);
        }
    }

//    fun setLargeImageFromUri(url: Uri?, imageView: ImageView) {
//        url.let {
//            Glide.with(this)
//                .load(url)
//                .override(400, 400)
//                .error(R.drawable.illust_sally_blank_1_1)
//                .centerCrop()
//                .thumbnail(0.2f)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView)
//        }
//    }
//
//    fun setCircleImageByGlide(iv: ImageView, url: String) {
//        Glide.with(this)
//            .load(url).placeholder(R.drawable.bg_round_white_gray)
//            .error(R.drawable.illust_sally_profile_blank)
//            .centerCrop()
//            .circleCrop()
//            .thumbnail(0.1f)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(iv)
//    }

    fun getErrorState(loadState: CombinedLoadStates): LoadState.Error? {
        return when {
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }
    }

    /**
     * ????????? ????????? ??? observe ??????.
     * ex)databinding observe..
     */
    protected abstract fun initDataBinding()

    /**
     * ??? ?????? ????????? ?????? ????????? ???????????? ??????.
     * ?????? ????????? ???
     */
    protected abstract fun initAfterBinding()

    open val startForResult =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra("")
            }
        }

}