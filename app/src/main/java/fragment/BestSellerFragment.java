package fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;

import Adapter.BestSellerAdapter;
import Model.BestsellerModel;
import e.wolfsoft1.pizza_order_animation.MyImageView;
import e.wolfsoft1.pizza_order_animation.R;


public class BestSellerFragment extends Fragment {

    //    ImageView bestseller_food_image;

    private GestureDetector mDetector;
    private int mPieRotation;
    MyImageView mImageView;
    Scroller mScroller;
    private ObjectAnimator mAutoCenterAnimator;
    private ValueAnimator mScrollAnimator;


    public static final int FLING_VELOCITY_DOWNSCALE = 4;

    Animation animation;
    LinearLayout linear_vertical_main;

    private RecyclerView bestseller_recyclerview;
    private ArrayList<BestsellerModel> bestsellerModels;
    private BestSellerAdapter bestSeller_adapter;

    Integer bestseller_food_image[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img2, R.drawable.img1};

    String bestseller_pizza_store[] = {"Hawaiian Pizza", "Smoked Salmon", "Pepperoni", "Dark Olives & Meat", "Hawaiian Pizza"};

    String bestseller_pizza_type[] = {"Smoked Bacon, Roasted Red\n" +
            "Peppers, Mozzarella Cheese", "Smoked Salmon, Roasted Garlic,\n" +
            "Cream Cheese", "Pepperoni, Tomato Sauce,\n" +
            "Mozzarella Cheese", "Smoked Bacon, Roasted Red\n" +
            "Peppers, Mozzarella Cheese", "Smoked Salmon, Roasted Garlic,\n" +
            "Cream Cheese"};

    String bestseller_pizza_rate[] = {"$20", "$30", "$25", "$20", "$30"};

    @SuppressLint("ObjectAnimatorBinding")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bestseller_fragment, container, false);


        bestseller_recyclerview = view.findViewById(R.id.bestseller_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        bestseller_recyclerview.setLayoutManager(layoutManager);
        bestseller_recyclerview.setItemAnimator(new DefaultItemAnimator());
//        bestseller_recyclerview.getChildAdapterPosition(bestseller_recyclerview.getFocusedChild());

        bestsellerModels = new ArrayList<>();
        if (Build.VERSION.SDK_INT < 11) {
            mScroller = new Scroller(getActivity());
        } else {
            mScroller = new Scroller(getActivity(), null, true);
        }

        if (Build.VERSION.SDK_INT >= 11) {
            mAutoCenterAnimator = ObjectAnimator.ofInt(this, "rotation", 360);

            mAutoCenterAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });

        }

        mScrollAnimator = ValueAnimator.ofFloat(0, 1);
        mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                bestSeller_adapter.tickScrollAnimation();

            }
        });


        for (int i = 0; i < bestseller_food_image.length; i++) {
            BestsellerModel model = new BestsellerModel(bestseller_food_image[i], bestseller_pizza_store[i], bestseller_pizza_type[i], bestseller_pizza_rate[i]);
            bestsellerModels.add(model);
        }

        //we shoud implement gesture detector, otherwise we cannot get the user input.
        //we have created  GestureListener inner class.
        bestSeller_adapter = new BestSellerAdapter(getContext(), bestsellerModels, mScroller, mAutoCenterAnimator);
        BestSellerAdapter.GestureListener gestureListener = bestSeller_adapter.new GestureListener();

        mDetector = new GestureDetector(getActivity(), gestureListener);

        //disable the long press gesture.
        mDetector.setIsLongpressEnabled(false);

        mImageView = (MyImageView) view.findViewById(R.id.bestseller_food_image);

        mPieRotation = 0;

        // Create a Scroller to handle the fling gesture.


        bestseller_recyclerview.setAdapter(bestSeller_adapter);


        return view;

    }

    public boolean onTouchEvent(MotionEvent event) {

        boolean result = mDetector.onTouchEvent(event);

        if (!result) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                bestSeller_adapter.stopScrolling();
                result = true;
            }
        }
        return result;
    }


}

