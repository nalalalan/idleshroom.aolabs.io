package io.aolabs.idleshroom;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.concurrent.atomic.AtomicBoolean;

@CapacitorPlugin(name = "MushroomBoopRewardedAd")
public class MushroomBoopRewardedAdPlugin extends Plugin {
    @PluginMethod
    public void show(PluginCall call) {
        String adUnitId = call.getString("adUnitId", "");
        if (adUnitId.trim().isEmpty()) {
            finish(call, new AtomicBoolean(false), false, "missing_ad_unit", 0, "");
            return;
        }

        Activity activity = getActivity();
        AtomicBoolean finished = new AtomicBoolean(false);

        activity.runOnUiThread(() -> {
            MobileAds.initialize(activity, initializationStatus -> {});
            AdRequest request = new AdRequest.Builder().build();
            RewardedAd.load(activity, adUnitId, request, new RewardedAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    finish(call, finished, false, loadAdError.getMessage(), 0, "");
                }

                @Override
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                    rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            finish(call, finished, false, "dismissed", 0, "");
                        }
                    });

                    rewardedAd.show(activity, rewardItem -> {
                        finish(call, finished, true, "", rewardItem.getAmount(), rewardItem.getType());
                    });
                }
            });
        });
    }

    private void finish(PluginCall call, AtomicBoolean finished, boolean rewarded, String error, int amount, String type) {
        if (!finished.compareAndSet(false, true)) return;
        JSObject result = new JSObject();
        result.put("rewarded", rewarded);
        if (!error.isEmpty()) result.put("error", error);
        if (amount > 0) result.put("amount", amount);
        if (!type.isEmpty()) result.put("type", type);
        call.resolve(result);
    }
}
