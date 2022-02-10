package softtrack.product.health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FoodItemsViewStateAdapter extends FragmentStateAdapter {
    public FoodItemsViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        if (position == 0) {
            return new FoodItemsSearchPageFragment();
        } else if (position == 1) {
            return new FoodItemsFavoritesPageFragment();
        } else if (position == 2) {
            return new FoodItemsCustomMealPageFragment();
        }
        return new FoodItemsSearchPageFragment();
    }

    @Override
    public int getItemCount() {
        // Hardcoded, use lists
        return 3;
    }
}
