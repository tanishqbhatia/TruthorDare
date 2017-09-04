package com.tanishqbhatia.truthordare.utils.server;

import com.tanishqbhatia.instagramauthorization.engine.InstagramEngine;
import com.tanishqbhatia.instagramauthorization.exceptions.InstagramException;
import com.tanishqbhatia.instagramauthorization.interfaces.InstagramAPIResponseCallback;
import com.tanishqbhatia.instagramauthorization.objects.IGMedia;
import com.tanishqbhatia.instagramauthorization.objects.IGPagInfo;
import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.utils.toast.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanishq Bhatia on 01-09-2017 at 12:30.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class IGServerRequest {
    public void getInstagramPosts() {
        InstagramEngine.getInstance(App.get().getCurrentActivity()).getMediaForUser(App.get().getCurrentActivity(), new InstagramAPIResponseCallback<ArrayList<IGMedia>>() {
            @Override
            public void onResponse(ArrayList<IGMedia> responseObject, IGPagInfo pageInfo) {
                if(responseObject!= null) {
                    List<String> instagramPostsUrls = new ArrayList<>(responseObject.size());
                    for (IGMedia igMedia : responseObject) {
                        instagramPostsUrls.add(igMedia.getImages().getThumbnail().getUrl());
                    }
                    EventBus.getDefault().postSticky(instagramPostsUrls);
                } else new Toast().colorRed().priorityHigh().message("Unable to fetch instagram posts, please try again later.").show();
            }

            @Override
            public void onFailure(InstagramException exception) {

            }
        });
    }
}
