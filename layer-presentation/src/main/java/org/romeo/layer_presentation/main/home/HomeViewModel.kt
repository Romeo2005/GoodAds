package org.romeo.layer_presentation.main.home

import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_domain.use_cases.GetUserAdsUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class HomeViewModel(
    override val navigator: AppNavigator,
    private val getUserAdsUseCase: GetUserAdsUseCase,
    private val userRepository: UserRepository,
    private val adsRepository: AdsRepository
) : BaseViewModel<HomeViewState>() {

    private var ads = mutableListOf<UserAdsListItem>()

    override fun onViewInit() {
        runAsync {
            ads = getUserAdsUseCase.execute() as MutableList<UserAdsListItem>
            mStateLiveData.postValue(AppState.Success(HomeViewState(ads)))
        }
    }

    fun onPriceChanged(postPrice: Int, storyPrice: Int) {
        runAsync {
            userRepository.changePrices(postPrice, storyPrice)
        }
    }

    fun onAdClicked(id: String) {
        //navigator.navigate(homeToAdFullCommand)
    }

    fun onDeleteAdClicked(id: String) {
        runAsync {
            val adsNew = ads.toMutableList()
            adsNew.removeIf { ad ->
                if (ad is UserAdsListItem.AdListItem) ad.ad.id == id
                else false
            }

            mStateLiveData.postValue(AppState.Success(HomeViewState(adsNew)))
            ads = adsNew
            adsRepository.deleteAd(id)
        }
    }

    fun onEditAdClicked(id: String) {
        //navigator.navigate(homeToEditAdCommand)
    }

}