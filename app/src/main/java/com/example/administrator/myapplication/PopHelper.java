package com.example.administrator.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;


public class PopHelper {

    

    /**
     * PopulWindow底部选择框
     *
     * @param mActivity
     * @param listData populwindow中的选项
     */
    public static void showPopulWindow(final Activity mActivity, final List<MenuItemBean> listData, final ChooicePopDismissListener listener) {

        final View popView = LayoutInflater.from(mActivity.getBaseContext()).inflate(R.layout.pop_menu, null);
        final PopupWindow popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        final LinearLayout popParent = (LinearLayout) popView.findViewById(R.id.pop_layout);
        TextView cancelBtn = (TextView) popView.findViewById(R.id.cancelBtn);
        ListView listViewSelect = (ListView) popView.findViewById(R.id.listViewSelect);
        SelectListAdapter selectAdapater = new SelectListAdapter(mActivity, listData);
        listViewSelect.setAdapter(selectAdapater);

        final int heightList = setListViewheight(selectAdapater, listViewSelect, listData);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimationUpToBottom(popView, popParent, heightList, popWindow);
                if (null != listener) {
                    listener.doAfterDismiss();
                }
            }
        });


        listViewSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.chooseItem(position);
                doAnimationUpToBottom(popView, popParent, heightList, popWindow);
                if (null != listener) {
                    listener.doAfterDismiss();
                }
            }
        });
        popView.setFocusable(true);
        popView.setFocusableInTouchMode(true);
        popWindow.setFocusable(true);
//        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);
//        popWindow.setAnimationStyle(R.style.PopupAnimation);
        popWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
        doAnimationBottomToUp(popView, popParent, heightList);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (null != listener) {
                    listener.doAfterDismiss();
                }
            }
        });
        popView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    doAnimationUpToBottom(popView, popParent, heightList, popWindow);
                    if (null != listener) {
                        listener.doAfterDismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        popView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = popView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        doAnimationUpToBottom(popView, popParent, heightList, popWindow);
                        if (null != listener) {
                            listener.doAfterDismiss();
                        }
                    }
                }
                return true;
            }
        });
    }




    /**
     * bottom to up animation
     *
     * @param viewBack
     * @param view
     * @param height
     */
    public static void doAnimationBottomToUp(View viewBack, View view, int height) {
        AnimatorSet set = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            int temHeight = 0;
            if (height == 0) {
                temHeight = (int) view.getResources().getDimension(R.dimen.dp_400);
            } else {
                temHeight = height;
            }
            ObjectAnimator animatorTrans = ObjectAnimator.ofFloat(view, "translationY", temHeight, 0.0f);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(viewBack, "alpha", 0.0f, 1.0f);
            set.playTogether(animatorAlpha, animatorTrans);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.setDuration(300);
            set.start();
        }

    }

    /**
     * top ti bottom animation
     *
     * @param viewBack
     * @param view
     * @param height
     * @param popWindow
     */
    public static void doAnimationUpToBottom(View viewBack, View view, int height, final PopupWindow popWindow) {
        AnimatorSet set = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            int temHeight = 0;
            if (height == 0) {
                temHeight = (int) view.getResources().getDimension(R.dimen.dp_400);
            } else {
                temHeight = height;
            }
            ObjectAnimator animatorTrans = ObjectAnimator.ofFloat(view, "translationY", 0.0f, temHeight);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(viewBack, "alpha", 1.0f, 0.0f);
            set.playTogether(animatorAlpha, animatorTrans);
            set.setInterpolator(new DecelerateInterpolator());
            set.setDuration(300);
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    popWindow.dismiss();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set.start();
        }
    }


        /**设置listView高度 控制了高度*/
        public static int setListViewheight(BaseAdapter adapter, ListView listView, List<MenuItemBean> listData) {
            View listItem = adapter.getView(0, null, listView);
            listItem.measure(0, 0);

            int height = listItem.getMeasuredHeight() * 5;

            if(listData.size() > 6) {//控制listView的高度
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = height;
                listView.setLayoutParams(params);
            }else {
            }
            return height;
        }

}
