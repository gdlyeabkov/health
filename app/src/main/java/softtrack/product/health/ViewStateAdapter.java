package softtrack.product.health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewStateAdapter extends FragmentStateAdapter {

    public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        if (position == 0) {
            return new MainPageFragment();
        } else if (position == 1) {
            return new TogetherFragment();
        } else if (position == 2) {
            return new FitnessFragment();
        } else if (position == 3) {
            return new MyPageFragment();
        }
        return new MainPageFragment();
    }

    @Override
    public int getItemCount() {
        // Hardcoded, use lists
        return 4;
    }
}
