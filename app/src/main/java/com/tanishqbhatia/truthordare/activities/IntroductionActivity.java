package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.constants.AppCons;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

/**
 * Created by Tanishq Bhatia on 11-08-2017 at 16:13.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSkipButton(false);
        setColorTransitionsEnabled(true);
        setColorDoneText(ColorCons.WHITE);
        setDoneText("I'm ready");
        setIndicatorColor(ColorCons.WHITE, ColorCons.GREY_50);
        setSeparatorColor(ColorCons.GREY_50);
        setNextArrowColor(ColorCons.WHITE);
        setWizardMode(true);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle(AppCons.APP_NAME);
        sliderPage1.setDescription(AppCons.APP_DESCRIPTION);
        sliderPage1.setImageDrawable(AppCons.APP_LOGO);
        sliderPage1.setBgColor(ColorCons.BLUE_500);
        sliderPage1.setTitleColor(ColorCons.WHITE);
        sliderPage1.setDescColor(ColorCons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage1));


        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle(AppCons.TRUTH);
        sliderPage2.setDescription(AppCons.TRUTH_DESCRIPTION);
        sliderPage2.setImageDrawable(AppCons.APP_LOGO);
        sliderPage2.setBgColor(ColorCons.GREEN_500);
        sliderPage1.setTitleColor(ColorCons.WHITE);
        sliderPage1.setDescColor(ColorCons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle(AppCons.DARE);
        sliderPage3.setDescription(AppCons.DARE_DESCRIPTION);
        sliderPage3.setImageDrawable(AppCons.APP_LOGO);
        sliderPage3.setBgColor(ColorCons.RED_500);
        sliderPage1.setTitleColor(ColorCons.WHITE);
        sliderPage1.setDescColor(ColorCons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage3));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Methods.launch(IntroductionActivity.this, TermsandConditionsActivity.class);
    }
}