package com.tanishqbhatia.truthordare.utils.imageview;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;
import com.tanishqbhatia.truthordare.utils.methods.Methods;

/**
 * Created by Tanishq Bhatia on 22-08-2017 at 10:38.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class CustomImageView {
    private SimpleDraweeView imageView;

    public CustomImageView(SimpleDraweeView imageView) {
        this.imageView = imageView;
    }

    public CustomImageView setBorder() {
        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
        RoundingParams roundingParams = RoundingParams.asCircle();
        roundingParams.setBorder(ColorCons.BLACK, 2.0f);
        if (hierarchy == null) {
            Methods.showLog("CustomImageView", "setBorder()", "here");
            imageView.setHierarchy(new GenericDraweeHierarchyBuilder(imageView.getResources()).setRoundingParams(roundingParams).build());
        } else {
            hierarchy.setRoundingParams(roundingParams);
            imageView.setHierarchy(hierarchy);
        }
        return this;
    }

    public CustomImageView setScaleType() {
        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        imageView.setHierarchy(hierarchy);
        return this;
    }

    public CustomImageView setProgressBar() {
        imageView.getHierarchy().setProgressBarImage(new CircleProgressBarDrawable());
        return this;
    }

    public void load(String url) {
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .disableDiskCache()
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setRetainImageOnFailure(true)
                .build();
        imageView.setController(controller);
    }
    private void logScan(QualityInfo qualityInfo, boolean isFinalImage) {
        System.out.println(""+qualityInfo.getQuality());
    }

}