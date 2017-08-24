package com.tanishqbhatia.truthordare.utils.imageview;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.tanishqbhatia.truthordare.utils.constants.AppCons;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

/**
 * Created by Tanishq Bhatia on 22-08-2017 at 10:38.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class CustomImageView {
    private SimpleDraweeView imageView;

    public CustomImageView(SimpleDraweeView imageView) {
        this.imageView = imageView;
        this.imageView.getHierarchy().setPlaceholderImage(AppCons.PLACEHOLDER);
        this.imageView.getHierarchy().setRetryImage(AppCons.RETRY, ScalingUtils.ScaleType.CENTER_INSIDE);
        this.imageView.getHierarchy().setFailureImage(AppCons.RETRY, ScalingUtils.ScaleType.CENTER_INSIDE);
    }

    public CustomImageView setBorder() {
        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
        hierarchy.setPlaceholderImage(AppCons.CIRCULAR_PLACEHOLDER);
        RoundingParams roundingParams = RoundingParams.asCircle();
        roundingParams.setBorder(ColorCons.BLACK, 2.0f);
        if (hierarchy == null) {
            imageView.setHierarchy(new GenericDraweeHierarchyBuilder(imageView.getResources()).setRoundingParams(roundingParams).build());
        } else {
            hierarchy.setRoundingParams(roundingParams);
            imageView.setHierarchy(hierarchy);
        }
        return this;
    }

    public CustomImageView setScaleType() {
        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        imageView.setHierarchy(hierarchy);
        return this;
    }

    public CustomImageView setProgressBar() {
        imageView.getHierarchy().setProgressBarImage(new CircleProgressBarDrawable());
        return this;
    }

    public void load(String lowResUrl, String highResUrl) {
        DraweeController controller;
        Uri highResUri = Uri.parse(highResUrl);
        ImageRequest highResRequest = ImageRequestBuilder.newBuilderWithSource(highResUri)
                .setProgressiveRenderingEnabled(true)
                .disableDiskCache()
                .build();
        if (lowResUrl != null) {
            Uri lowResUri = Uri.parse(lowResUrl);
            ImageRequest lowResRequest = ImageRequestBuilder.newBuilderWithSource(lowResUri)
                    .disableDiskCache()
                    .build();
            controller = Fresco.newDraweeControllerBuilder()
                    .setLowResImageRequest(lowResRequest)
                    .setImageRequest(highResRequest)
                    .setTapToRetryEnabled(true)
                    .setRetainImageOnFailure(true)
                    .build();
        } else {
            controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(highResRequest)
                    .setTapToRetryEnabled(true)
                    .setRetainImageOnFailure(true)
                    .build();
        }
        imageView.setController(controller);
    }

}