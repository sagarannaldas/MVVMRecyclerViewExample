package in.techrebounce.mvvmrecyclerviewexample.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.techrebounce.mvvmrecyclerviewexample.models.NicePlaces;
import in.techrebounce.mvvmrecyclerviewexample.repositories.NicePlaceRepository;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlaces>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init() {
        if(mNicePlaces != null) {
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addValue (NicePlaces nicePlaces) {
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlaces> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlaces);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.setValue(false);
            }
        }.execute();
    }

    public LiveData<List<NicePlaces>> getNicePlaces() {
        return mNicePlaces;
    }

    public  LiveData<Boolean> IsUpdating() {
        return mIsUpdating;
    }
}
