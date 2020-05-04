/*
 * Copyright (c) 2017 - present, CV4J Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cv4j.app.activity.pixels;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.cv4j.app.R;
import com.cv4j.core.datamodel.CV4JImage;
import com.cv4j.core.datamodel.ImageProcessor;
import com.cv4j.core.pixels.Flip;
import com.safframework.injectview.annotations.InjectExtra;
import com.safframework.injectview.annotations.InjectView;
import com.safframework.injectview.annotations.OnClick;

/**
 * Created by tony on 2017/12/10.
 */

public class FlipActivity extends BaseActivity {

    @InjectView(R.id.image)
    ImageView image;

    @InjectView(R.id.result_image1)
    ImageView result1;

    @InjectView(R.id.result_image2)
    ImageView result2;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectExtra(key = "Title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip);

        initData();
    }

    private void initData() {

        toolbar.setTitle("< "+title);
        Resources res = getResources();

        final Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pixel_test_1);
        image.setImageBitmap(bitmap);

        CV4JImage cv4jImage = new CV4JImage(bitmap);
        ImageProcessor imageProcessor = cv4jImage.getProcessor();

        Flip.flip(imageProcessor,Flip.FLIP_HORIZONTAL);

        if (imageProcessor!=null) {
            CV4JImage resultCV4JImage = new CV4JImage(imageProcessor.getWidth(), imageProcessor.getHeight(), imageProcessor.getPixels());
            result1.setImageBitmap(resultCV4JImage.getProcessor().getImage().toBitmap());
        }

        cv4jImage = new CV4JImage(bitmap);
        ImageProcessor imageProcessor2 = cv4jImage.getProcessor();

        Flip.flip(imageProcessor2,Flip.FLIP_VERTICAL);

        if (imageProcessor2!=null) {
            CV4JImage resultCV4JImage = new CV4JImage(imageProcessor2.getWidth(), imageProcessor2.getHeight(), imageProcessor2.getPixels());
            result2.setImageBitmap(resultCV4JImage.getProcessor().getImage().toBitmap());
        }
    }

    @OnClick(id= R.id.toolbar)
    void clickToolbar() {

        finish();
    }
}
