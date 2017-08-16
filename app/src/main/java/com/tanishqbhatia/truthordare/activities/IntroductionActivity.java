package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.constants.Cons;

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
        setColorDoneText(Cons.WHITE);
        setDoneText("I'm ready");
        setIndicatorColor(Cons.WHITE, Cons.GREY_50);
        setSeparatorColor(Cons.GREY_50);
        setNextArrowColor(Cons.WHITE);
        setWizardMode(true);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle(Cons.APP_NAME);
        sliderPage1.setDescription(Cons.APP_DESCRIPTION);
        sliderPage1.setImageDrawable(Cons.APP_LOGO);
        sliderPage1.setBgColor(Cons.BLUE_500);
        sliderPage1.setTitleColor(Cons.WHITE);
        sliderPage1.setDescColor(Cons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage1));


        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle(Cons.TRUTH);
        sliderPage2.setDescription(Cons.TRUTH_DESCRIPTION);
        sliderPage2.setImageDrawable(Cons.APP_LOGO);
        sliderPage2.setBgColor(Cons.GREEN_500);
        sliderPage1.setTitleColor(Cons.WHITE);
        sliderPage1.setDescColor(Cons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle(Cons.DARE);
        sliderPage3.setDescription(Cons.DARE_DESCRIPTION);
        sliderPage3.setImageDrawable(Cons.APP_LOGO);
        sliderPage3.setBgColor(Cons.RED_500);
        sliderPage1.setTitleColor(Cons.WHITE);
        sliderPage1.setDescColor(Cons.GREY_50);
        addSlide(AppIntroFragment.newInstance(sliderPage3));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Methods.launch(IntroductionActivity.this, TermsandConditionsActivity.class);
    }
}