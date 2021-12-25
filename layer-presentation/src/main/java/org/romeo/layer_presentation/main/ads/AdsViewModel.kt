package org.romeo.layer_presentation.main.ads

import android.os.Bundle
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AdsToAdListItemFullCommand

class AdsViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository,
    private val command: AdsToAdListItemFullCommand
) : BaseViewModel<Ads>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Success(adsRepository.getOtherAds()))
        }
    }

    fun onAdClicked(adId: String) {
        navigator.navigate(command, Bundle().apply { putString(AD_FULL_KEY, adId) })
    }

}