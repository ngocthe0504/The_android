package pk_lichsuhonhang;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.test.fragment_ordered.DaGiaoFragment;
import com.example.test.fragment_ordered.DaHuy_Fragment;
import com.example.test.fragment_ordered.DangGiao_Fragment;


public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new DaGiaoFragment();
            case 2:
                return new DaHuy_Fragment();
            case 0:
            default:
                return new DangGiao_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
