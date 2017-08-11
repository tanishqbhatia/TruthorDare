package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.tanishqbhatia.truthordare.utils.Cons;

/**
 * Created by Tanishq Bhatia on 11-08-2017 at 16:13.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(Cons.APP_NAME);
        sliderPage.setDescription(Cons.APP_DESCRIPTION);
        sliderPage.setImageDrawable(Cons.APP_LOGO);
        sliderPage.setBgColor(Cons.GREY_50);
        sliderPage.setTitleColor(Cons.BLACK);
        sliderPage.setDescColor(Cons.GREY_700);
        addSlide(AppIntroFragment.newInstance(sliderPage));
        addSlide(AppIntroFragment.newInstance(sliderPage));
        addSlide(AppIntroFragment.newInstance(sliderPage));
        addSlide(AppIntroFragment.newInstance(sliderPage));
        setFadeAnimation();
        setColorSkipButton(Cons.BLACK);
        setColorDoneText(Cons.BLACK);

    }


}
