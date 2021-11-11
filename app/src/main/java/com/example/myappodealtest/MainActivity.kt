package com.example.myappodealtest

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appodeal.ads.Appodeal
import com.appodeal.ads.RewardedVideoCallbacks
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var placementName: String
    private var nativeAdapter: NativeAdapter? = null
    var dataSet: MutableList<RowType> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var countOfBannerShow = 0
        var countOfRewardedVideo = 0
        val appKey = "dd05f7cb60f7471ce4121123e83f81cd3e0865fa07c30e65"
        val buttonShowBanner: Button = findViewById(R.id.banners_btn)
        val buttonShowInterstitials: Button = findViewById(R.id.interstitials_btn)
        val buttonRewardedVideo: Button = findViewById(R.id.rewarded_btn)
        val buttonNative: Button = findViewById(R.id.native_btn)
        placementName = "default"



        Appodeal.initialize(
            this,
            appKey,
            Appodeal.BANNER_VIEW or Appodeal.INTERSTITIAL or Appodeal.REWARDED_VIDEO or Appodeal.NATIVE
        )
        Appodeal.setTesting(true)
        Appodeal.setBannerViewId(R.id.appodeal_banner_view)

        buttonShowBanner.setOnClickListener {
            countOfBannerShow++
            Appodeal.show(this, Appodeal.BANNER_VIEW)
            if (countOfBannerShow == 5) {
                buttonShowBanner.setEnabled(false)
                Appodeal.hide(this, Appodeal.BANNER_VIEW)
            }
        }
        buttonShowInterstitials.setOnClickListener {
            Appodeal.hide(this, Appodeal.BANNER_VIEW)
            Appodeal.show(this, Appodeal.INTERSTITIAL)
            buttonShowInterstitials.setEnabled(false)
            buttonShowInterstitials.postDelayed(Runnable {
                buttonShowInterstitials.setEnabled(true)
            }, 60000)

        }
        buttonRewardedVideo.setEnabled(false)
        Appodeal.setRewardedVideoCallbacks(object : RewardedVideoCallbacks {
            override fun onRewardedVideoLoaded(p0: Boolean) {
                if (countOfRewardedVideo < 3) {
                    buttonRewardedVideo.setEnabled(true)
                }else {
                    buttonRewardedVideo.setEnabled(false)
                }
                countOfRewardedVideo++
                showToast("Video Loaded")

            }

            override fun onRewardedVideoFailedToLoad() {

            }

            override fun onRewardedVideoShown() {
                showToast("Show Video")
            }

            override fun onRewardedVideoShowFailed() {
            }

            override fun onRewardedVideoFinished(p0: Double, p1: String?) {
                showToast(
                    "Rewarded Video Finished! \n" +
                            "Get your reward"
                )
            }

            override fun onRewardedVideoClosed(p0: Boolean) {
            }

            override fun onRewardedVideoExpired() {
            }

            override fun onRewardedVideoClicked() {
            }
        })
        buttonRewardedVideo.setOnClickListener {
            Appodeal.show(this, Appodeal.REWARDED_VIDEO)
        }

        Appodeal.cache(this, Appodeal.NATIVE)

        buttonNative.setOnClickListener {
            Appodeal.hide(this, Appodeal.BANNER_VIEW)
            Appodeal.cache(this, Appodeal.NATIVE, 4)
            var nativeAds = Appodeal.getNativeAds(4)
            for (i in 1..20) {
                dataSet.add(Native())
            }
            val recyclerView = findViewById<RecyclerView>(R.id.recycler)
            nativeAdapter = NativeAdapter(dataSet, nativeAds)
            recyclerView.adapter = nativeAdapter
            recyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        }

    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

}


