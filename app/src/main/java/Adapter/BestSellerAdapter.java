package Adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;

import Model.BestsellerModel;
import e.wolfsoft1.pizza_order_animation.PepperoniPizza;
import e.wolfsoft1.pizza_order_animation.R;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.MyViewHolder> {

    public static final int FLING_VELOCITY_DOWNSCALE = 4;
    private final Scroller mScroller;
    private ObjectAnimator mAutoCenterAnimator;
    private int mPieRotation;


    Animation animrotateTrans;
    Context context;
    private ArrayList<BestsellerModel> bestsellerModels;
    ImageView bestseller_food_image;
    private ValueAnimator mScrollAnimator;

    public BestSellerAdapter(Context context, ArrayList<BestsellerModel> bestsellerModels, Scroller mScroller, ObjectAnimator mAutoCenterAnimator) {
        this.context = context;
        this.bestsellerModels = bestsellerModels;
        this.mScroller = mScroller;
        this.mAutoCenterAnimator = mAutoCenterAnimator;
    }

    @NonNull
    @Override
    public BestSellerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bestseller, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BestSellerAdapter.MyViewHolder myViewHolder, final int i) {

        final BestsellerModel model = bestsellerModels.get(i);
        bestseller_food_image.setImageResource(model.getBestseller_food_image());
        myViewHolder.bestseller_pizza_store.setText(model.getBestseller_pizza_store());
        myViewHolder.bestseller_pizza_type.setText(model.getBestseller_pizza_type());
        myViewHolder.bestseller_pizza_rate.setText(model.getBestseller_pizza_rate());

        myViewHolder.customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PepperoniPizza.class);
                intent.putExtra("price", model.getBestseller_pizza_rate());

                View sharedView = bestseller_food_image;
                String transitionName = context.getString(R.string.blue_name);

                intent.putExtra("image", model.getBestseller_food_image());
                ActivityOptions transitionActivityOptions = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation((Activity) context, sharedView, transitionName);

//                animrotateTrans = AnimationUtils.loadAnimation(context,
//                        R.anim.rotate);
//
//                bestseller_food_image.startAnimation(animrotateTrans);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    context.startActivity(intent, transitionActivityOptions.toBundle());
                } else {
                    context.startActivity(intent);
                }

                notifyDataSetChanged();
            }
        });

      bestseller_food_image.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onScrollFinished();

          }
      });
    }

    @Override
    public int getItemCount() {
        return bestsellerModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView customize;
        TextView bestseller_pizza_store, bestseller_pizza_type, bestseller_pizza_rate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bestseller_food_image = itemView.findViewById(R.id.bestseller_food_image);
            bestseller_pizza_store = itemView.findViewById(R.id.bestseller_pizza_store);
            bestseller_pizza_type = itemView.findViewById(R.id.bestseller_pizza_type);
            bestseller_pizza_rate = itemView.findViewById(R.id.bestseller_pizza_rate);
            customize = itemView.findViewById(R.id.customize);
        }
    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener {

        //when you try to rotate the image. onscroll will be called behind the scene.
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // Set the rotation directly.
            float scrolltorotte = vectorToScalarScroll(
                    distanceX,
                    distanceY,
                    e2.getX() - ((bestseller_food_image.getWidth()) + bestseller_food_image.getLeft()),
                    e2.getY() - ((bestseller_food_image.getHeight()) + bestseller_food_image.getTop()));

            setPieRotation(getPieRotation() - (int) scrolltorotte / FLING_VELOCITY_DOWNSCALE);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float scrolltorotte = vectorToScalarScroll(
                    velocityX,
                    velocityY,
                    e2.getX() - ((bestseller_food_image.getWidth()) + bestseller_food_image.getLeft()),
                    e2.getY() - ((bestseller_food_image.getHeight()) + bestseller_food_image.getTop()));

            mScroller.fling(
                    0,
                    (int) getPieRotation(),
                    0,
                    (int) scrolltorotte / FLING_VELOCITY_DOWNSCALE,
                    0,
                    0,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE);

            if (Build.VERSION.SDK_INT >= 11) {
                mScrollAnimator.setDuration(mScroller.getDuration());
                mScrollAnimator.start();
            }
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            if (isAnimationRunning()) {
                stopScrolling();
            }
            return true;
        }

    }

    private boolean isAnimationRunning() {
        return !mScroller.isFinished() || (Build.VERSION.SDK_INT >= 11 && mAutoCenterAnimator.isRunning());
    }

    private static float vectorToScalarScroll(float dx, float dy, float x, float y) {
        // get the length of the vector
        float l = (float) Math.sqrt(dx * dx + dy * dy);

        // decide if the scalar should be negative or positive by finding
        // the dot product of the vector perpendicular to (x,y).
        float crossX = -y;
        float crossY = x;

        float dot = (crossX * dx + crossY * dy);
        float sign = Math.signum(dot);

        return l * sign;
    }


    public void setPieRotation(int rotation) {
        rotation = (rotation % 360 + 360) % 360;
        mPieRotation = rotation;
        bestseller_food_image.setRotation(rotation);
    }


    public int getPieRotation() {
        return mPieRotation;
    }

    public void tickScrollAnimation() {
        if (!mScroller.isFinished()) {
            mScroller.computeScrollOffset();
            setPieRotation(mScroller.getCurrY());
        } else {
            if (Build.VERSION.SDK_INT >= 11) {
                mScrollAnimator.cancel();
            }
            onScrollFinished();
        }
    }

    public void stopScrolling() {
        mScroller.forceFinished(true);
        if (Build.VERSION.SDK_INT >= 11) {
            mAutoCenterAnimator.cancel();
        }

        onScrollFinished();
    }

    /**
     * Called when the user finishes a scroll action.
     */
    private void onScrollFinished() {
    }
}
