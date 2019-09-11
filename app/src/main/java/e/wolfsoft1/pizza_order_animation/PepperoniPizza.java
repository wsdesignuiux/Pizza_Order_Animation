package e.wolfsoft1.pizza_order_animation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PepperoniPizza extends AppCompatActivity {

    ImageView small_pizza;
    LinearLayout linear_large_pizza, linear_medium_pizza, linear_small_pizza;

    FrameLayout small_pizza_framelayout, medium_pizza_framelayout, large_pizza_framelayout;

    ImageView decrement, increment;
    TextView display_value_cart;


    int count = 0;

    FrameLayout large_shadow_framelayout, small_shadow_framelayout, frame;


    boolean paneerselected = false;
    boolean onionselected = false;
    boolean redpaprica = false;

    TextView add_to_cart_text;


    Animation animZoomIn, animZoomOut, animZoommedium, animMoveLeft, animFadeIn, animFadeOut, animMove, animZoomOutMediumtoLarge,
            animZoomInLargetoSmall, animZoomInSmalltoMedium, animZoomInSmalltoLarge,
            animZoomOutSmallTopping, animZoomOutMediumTopping, animZoomOutLargeTopping,
            animZoomOutLargeTopping3, animZoomOutLargeTopping2, animZoomOutMediumTopping2, animZoomOutMediumTopping3,
            animZoomOutSmallTopping2, animZoomOutSmallTopping3, animMovefinal, animZoom;


    int value;

    ImageView paneer_topping, onion_topping, redpaprika_topping;

    TextView small_pizza_text_size, small_pizza_text_price, medium_pizza_text_size, medium_pizza_text_price, large_pizza_text_size, large_pizza_text_price;
    LinearLayout paneer_cardview, onion_cardview, redpaprika_cardview;

    ImageView paneer_selected, onion_selected, redpaprika_selected;
    LinearLayout large_pizza_shadow_circle, medium_pizza_shadow_circle, small_pizza_shadow_circle;

    String pizza_price;
    int value_per_pizza;

    int result;

    //toolbar
    TextView price_of_pizza;
    ImageView back;
    private int total_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pepperoni__pizza);

        supportPostponeEnterTransition();

        small_pizza = findViewById(R.id.small_pizza);
        price_of_pizza = findViewById(R.id.price_of_pizza);

        Intent intent = getIntent();
        int pic = intent.getIntExtra("image", 0);
        small_pizza.setImageResource(pic);
        pizza_price = intent.getStringExtra("price");
        price_of_pizza.setText(pizza_price);
        value_per_pizza = Integer.valueOf(pizza_price.substring(1));

        small_shadow_framelayout = findViewById(R.id.small_shadow_framelayout);

        back = findViewById(R.id.back);
        decrement = findViewById(R.id.decrement);
        increment = findViewById(R.id.increment);

        small_pizza_framelayout = findViewById(R.id.small_pizza_framelayout);
        medium_pizza_framelayout = findViewById(R.id.medium_pizza_framelayout);
        large_pizza_framelayout = findViewById(R.id.large_pizza_framelayout);

        display_value_cart = findViewById(R.id.display_value_cart);

        small_pizza_text_size = findViewById(R.id.small_pizza_text_size);
        medium_pizza_text_size = findViewById(R.id.medium_pizza_text_size);
        large_pizza_text_size = findViewById(R.id.large_pizza_text_size);

        small_pizza_text_price = findViewById(R.id.small_pizza_text_price);
        medium_pizza_text_price = findViewById(R.id.medium_pizza_text_price);
        large_pizza_text_price = findViewById(R.id.large_pizza_text_price);

        linear_large_pizza = findViewById(R.id.linear_large_pizza);
        linear_medium_pizza = findViewById(R.id.linear_medium_pizza);
        linear_small_pizza = findViewById(R.id.linear_small_pizza);

        paneer_cardview = findViewById(R.id.paneer_cardview);
        onion_cardview = findViewById(R.id.onion_cardview);
        redpaprika_cardview = findViewById(R.id.redpaprika_cardview);

        paneer_topping = findViewById(R.id.paneer_topping);
        onion_topping = findViewById(R.id.onion_topping);
        redpaprika_topping = findViewById(R.id.redpaprika_topping);

        paneer_selected = findViewById(R.id.paneer_selected);
        onion_selected = findViewById(R.id.onion_selected);
        redpaprika_selected = findViewById(R.id.redpaprika_selected);

        add_to_cart_text = findViewById(R.id.add_to_cart_text);

        price_of_pizza.setText("");
        price_of_pizza.setText(pizza_price);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movedata);

        animMoveLeft = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movedataleft);

        linear_small_pizza.startAnimation(animMoveLeft);
        linear_large_pizza.startAnimation(animMove);


        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    int price = value_per_pizza * count;
                    price_of_pizza.setText("$" + String.valueOf(price));
                    display_value_cart.setText(String.valueOf(count));

                } else {
                    if (count > 0) {
                        count--;
                        display_value_cart.setText(String.valueOf(count));
                    }
                }


            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                display_value_cart.setText(String.valueOf(count));
                int price = value_per_pizza * count;
                price_of_pizza.setText("$" + String.valueOf(price));

            }
        });

        large_shadow_framelayout = findViewById(R.id.large_shadow_framelayout);
        animZoom = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom);

        large_shadow_framelayout.startAnimation(animZoom);

        small_pizza_framelayout.setVisibility(View.GONE);
        medium_pizza_framelayout.setVisibility(View.GONE);
        large_pizza_framelayout.setVisibility(View.VISIBLE);

        linear_small_pizza.setBackgroundResource(R.drawable.grey_rect);
        linear_medium_pizza.setBackgroundResource(R.drawable.grey_rect);
        linear_large_pizza.setBackgroundResource(R.drawable.shadow_circle);

        small_pizza_text_size.setTextSize(15);
        medium_pizza_text_size.setTextSize(15);
        large_pizza_text_size.setTextSize((float) 16.7);

        small_pizza_text_price.setTextSize(10);
        medium_pizza_text_price.setTextSize(10);
        large_pizza_text_price.setTextSize((float) 11.7);


        small_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
        medium_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
        large_pizza_text_size.setTextColor(Color.parseColor("#272a33"));


        small_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
        medium_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
        large_pizza_text_price.setTextColor(Color.parseColor("#272a33"));


        paneer_topping.setVisibility(View.INVISIBLE);
        onion_topping.setVisibility(View.INVISIBLE);
        redpaprika_topping.setVisibility(View.INVISIBLE);

        value = 0;

        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);

        animZoomInLargetoSmall = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in_large_to_small);


        linear_small_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                small_pizza_framelayout.setVisibility(View.VISIBLE);
                medium_pizza_framelayout.setVisibility(View.GONE);
                large_pizza_framelayout.setVisibility(View.GONE);

                linear_small_pizza.setBackgroundResource(R.drawable.shadow_circle);
                linear_medium_pizza.setBackgroundResource(R.drawable.grey_rect);
                linear_large_pizza.setBackgroundResource(R.drawable.grey_rect);

                small_pizza_text_size.setTextColor(Color.parseColor("#272a33"));
                medium_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
                large_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));

                small_pizza_text_size.setTextSize((float) 16.7);
                medium_pizza_text_size.setTextSize(15);
                large_pizza_text_size.setTextSize(15);

                small_pizza_text_price.setTextSize((float) 11.7);
                medium_pizza_text_price.setTextSize(10);
                large_pizza_text_price.setTextSize(10);


                small_pizza_text_price.setTextColor(Color.parseColor("#272a33"));
                medium_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
                large_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));


                paneer_topping.setVisibility(View.GONE);
                onion_topping.setVisibility(View.GONE);
                redpaprika_topping.setVisibility(View.GONE);


                price_of_pizza.setText("$15");
                value_per_pizza = Integer.valueOf(price_of_pizza.getText().toString().substring(1));

                switch (value) {
                    case 0:
                        break;
                    case 1:
                        small_pizza.startAnimation(animZoomOut);
                        break;
                    case 2:
                        small_pizza.startAnimation(animZoomInLargetoSmall);
                        break;

                }

                value = 0;
                paneer_topping.clearAnimation();
                paneer_topping.clearFocus();
                onion_topping.clearAnimation();
                onion_topping.clearFocus();
                redpaprika_topping.clearAnimation();
                redpaprika_topping.clearFocus();

                paneer_selected.setVisibility(View.INVISIBLE);
                onion_selected.setVisibility(View.INVISIBLE);
                redpaprika_selected.setVisibility(View.INVISIBLE);
            }
        });

        animZoommedium = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_medium);


        animZoomInSmalltoMedium = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in_small_to_medium);

        linear_medium_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*large_pizza_shadow_circle.setVisibility(View.VISIBLE);*/

                small_pizza_framelayout.setVisibility(View.GONE);
                medium_pizza_framelayout.setVisibility(View.VISIBLE);
                large_pizza_framelayout.setVisibility(View.GONE);

                linear_small_pizza.setBackgroundResource(R.drawable.grey_rect);
                linear_medium_pizza.setBackgroundResource(R.drawable.shadow_circle);
                linear_large_pizza.setBackgroundResource(R.drawable.grey_rect);

                small_pizza_text_size.setTextSize(15);
                medium_pizza_text_size.setTextSize((float) 16.7);
                large_pizza_text_size.setTextSize(15);

                small_pizza_text_price.setTextSize(10);
                medium_pizza_text_price.setTextSize((float) 11.7);
                large_pizza_text_price.setTextSize(10);

                small_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
                medium_pizza_text_size.setTextColor(Color.parseColor("#272a33"));
                large_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));

                small_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
                medium_pizza_text_price.setTextColor(Color.parseColor("#272a33"));
                large_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));

                paneer_topping.setVisibility(View.GONE);
                onion_topping.setVisibility(View.GONE);
                redpaprika_topping.setVisibility(View.GONE);

                price_of_pizza.setText("$25");
                value_per_pizza = Integer.valueOf(price_of_pizza.getText().toString().substring(1));

                switch (value) {
                    case 0:
                        small_pizza.startAnimation(animZoomInSmalltoMedium);
                        break;

                    case 1:
                        break;
                    case 2:
                        small_pizza.startAnimation(animZoommedium);
                        break;
                }
                value = 1;

                paneer_topping.clearAnimation();
                paneer_topping.clearFocus();
                onion_topping.clearAnimation();
                onion_topping.clearFocus();
                redpaprika_topping.clearAnimation();
                redpaprika_topping.clearFocus();

                paneer_selected.setVisibility(View.INVISIBLE);
                onion_selected.setVisibility(View.INVISIBLE);
                redpaprika_selected.setVisibility(View.INVISIBLE);


            }
        });

        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        animZoomInSmalltoLarge = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in_small_to_large);

        linear_large_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                small_pizza_framelayout.setVisibility(View.GONE);
                medium_pizza_framelayout.setVisibility(View.GONE);
                large_pizza_framelayout.setVisibility(View.VISIBLE);

                linear_small_pizza.setBackgroundResource(R.drawable.grey_rect);
                linear_medium_pizza.setBackgroundResource(R.drawable.grey_rect);
                linear_large_pizza.setBackgroundResource(R.drawable.shadow_circle);


                small_pizza_text_size.setTextSize(15);
                medium_pizza_text_size.setTextSize(15);
                large_pizza_text_size.setTextSize((float) 16.7);

                small_pizza_text_price.setTextSize(10);
                medium_pizza_text_price.setTextSize(10);
                large_pizza_text_price.setTextSize((float) 11.7);


                small_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
                medium_pizza_text_size.setTextColor(Color.parseColor("#a6a8bc"));
                large_pizza_text_size.setTextColor(Color.parseColor("#272a33"));


                small_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
                medium_pizza_text_price.setTextColor(Color.parseColor("#a6a8bc"));
                large_pizza_text_price.setTextColor(Color.parseColor("#272a33"));


                paneer_topping.setVisibility(View.INVISIBLE);
                onion_topping.setVisibility(View.INVISIBLE);
                redpaprika_topping.setVisibility(View.INVISIBLE);

                price_of_pizza.setText("$35");
                value_per_pizza = Integer.valueOf(price_of_pizza.getText().toString().substring(1));


                switch (value) {
                    case 0:
                        small_pizza.startAnimation(animZoomInSmalltoLarge);
                        break;
                    case 1:
                        small_pizza.startAnimation(animZoomIn);
                        break;
                    case 2:

                        break;
                }


                value = 2;

                paneer_topping.clearAnimation();
                paneer_topping.clearFocus();
                onion_topping.clearAnimation();
                onion_topping.clearFocus();
                redpaprika_topping.clearAnimation();
                redpaprika_topping.clearFocus();

                paneer_selected.setVisibility(View.INVISIBLE);
                onion_selected.setVisibility(View.INVISIBLE);
                redpaprika_selected.setVisibility(View.INVISIBLE);

            }
        });

        // Zoom Out

        animZoomOutSmallTopping = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_small_topping);

        animZoomOutSmallTopping2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_small_topping2);

        animZoomOutSmallTopping3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_small_topping3);

//        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.zoom_out);
        // fade in
        paneer_cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (paneerselected == false) {
                    paneer_selected.setVisibility(View.VISIBLE);
                    paneer_topping.setVisibility(View.VISIBLE);
//                    paneer_topping.startAnimation(animZoomOutSmallTopping);

                    value_per_pizza = value_per_pizza + 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));
                    switch (value) {

                        case 0:
                            paneer_topping.startAnimation(animZoomOutSmallTopping);
                            break;
                        case 1:
                            paneer_topping.startAnimation(animZoomOutMediumTopping);
                            break;
                        case 2:
                            paneer_topping.startAnimation(animZoomOutLargeTopping);
                            break;
                    }

                    paneerselected = true;
                } else {
                    value_per_pizza = value_per_pizza - 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));

                    paneer_topping.clearAnimation();
                    paneer_topping.clearFocus();
                    paneer_topping.setVisibility(View.GONE);
                    paneer_selected.setVisibility(View.INVISIBLE);
                    paneerselected = false;
                }
            }
        });

        animZoomOutMediumTopping = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_medium_topping);
        animZoomOutMediumTopping2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_medium_topping2);
        animZoomOutMediumTopping3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_medium_topping3);


        // fade in
        onion_cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (onionselected == false) {
                    onion_selected.setVisibility(View.VISIBLE);
                    onion_topping.setVisibility(View.VISIBLE);
                    value_per_pizza = value_per_pizza + 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));
                    switch (value) {
                        case 0:
                            onion_topping.startAnimation(animZoomOutSmallTopping2);
                            break;
                        case 1:
                            onion_topping.startAnimation(animZoomOutMediumTopping2);
                            break;
                        case 2:
                            onion_topping.startAnimation(animZoomOutLargeTopping2);
                            break;


                    }

                    onionselected = true;

                } else {

                    value_per_pizza = value_per_pizza - 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));
                    onion_topping.clearAnimation();
                    onion_topping.clearFocus();
                    onion_topping.setVisibility(View.GONE);
                    onion_selected.setVisibility(View.INVISIBLE);
                    onionselected = false;
                }

            }
        });

        animZoomOutLargeTopping2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_large_topping2);

        animZoomOutLargeTopping = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_large_topping);

        animZoomOutLargeTopping3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out_large_topping3);


        // fade in
        redpaprika_cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (redpaprica == false) {
                    redpaprika_selected.setVisibility(View.VISIBLE);
                    redpaprika_topping.setVisibility(View.VISIBLE);

                    value_per_pizza = value_per_pizza + 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));

                    switch (value) {

                        case 0:
                            redpaprika_topping.startAnimation(animZoomOutSmallTopping3);
                            break;
                        case 1:
                            redpaprika_topping.startAnimation(animZoomOutMediumTopping3);
                            break;
                        case 2:
                            redpaprika_topping.startAnimation(animZoomOutLargeTopping3);
                            break;
                    }

                    redpaprica = true;


                } else {
                    value_per_pizza = value_per_pizza - 2;
                    price_of_pizza.setText(String.valueOf("$" + value_per_pizza));
                    redpaprika_topping.clearAnimation();
                    redpaprika_topping.clearFocus();
                    redpaprika_topping.setVisibility(View.GONE);
                    redpaprika_selected.setVisibility(View.INVISIBLE);
                    redpaprica = false;
                }
            }
        });


//        result = price_of_pizza*2;


        add_to_cart_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.temp);

                animMovefinal = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.temp_final_animation);

                paneer_topping.startAnimation(animMovefinal);
                onion_topping.startAnimation(animMovefinal);
                redpaprika_topping.startAnimation(animMovefinal);

                // Move

                // fade out
                small_pizza.setVisibility(View.VISIBLE);
                small_pizza.startAnimation(animMove);

                large_shadow_framelayout.setVisibility(View.INVISIBLE);
                small_shadow_framelayout.setVisibility(View.INVISIBLE);


                Intent intent = new Intent(PepperoniPizza.this, Custom1.class);
                startActivity(intent);

            }
        });

        supportStartPostponedEnterTransition();


    }

    @Override
    public void onBackPressed() {
        //To support reverse transitions when user clicks the device back button

        supportFinishAfterTransition();
        super.onBackPressed();
    }
}
