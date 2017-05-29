package com.example.andrzejdevcom.sdacademyaplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.andrzejdevcom.sdacademyaplikacja.component.DaggerServiceComponent;
import com.example.andrzejdevcom.sdacademyaplikacja.component.ServiceComponent;
import com.example.andrzejdevcom.sdacademyaplikacja.model.Card;
import com.example.andrzejdevcom.sdacademyaplikacja.model.Deck;
import com.example.andrzejdevcom.sdacademyaplikacja.module.ServiceModule;
import com.example.andrzejdevcom.sdacademyaplikacja.service.CardService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ServiceComponent serviceComponent;

    @Inject
    Retrofit retrofit;

    String url = "https://deckofcardsapi.com/api/deck/";
    private CardService.CardApi cardApi;

    @BindView(R.id.spinner)
    Spinner spinner;

    private Integer[] arraySpinner;
    private String deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectServiceComponent();
        configureSpinnerData();
    }

    @OnClick(R.id.buttonGetCards)
    public void startGame() {
        int count = (int) spinner.getSelectedItem();
        fetchDeck(count);
        Utils.showToast(getApplicationContext(), spinner.getSelectedItem().toString());
    }

    @OnClick(R.id.buttonDrawCard)
    public void drawCard() {
        fetchCardFromDeckID(deckId, 1);
    }

    private void configureSpinnerData() {
        this.arraySpinner = new Integer[]{
                1, 2, 3, 4, 5
        };
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinner.setAdapter(adapter);
    }

    private void injectServiceComponent() {
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(url))
                .build();
        serviceComponent.inject(this);
        cardApi = retrofit.create(CardService.CardApi.class);
    }

    public void fetchDeck(final int count) {
        cardApi.fetchDeck(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Deck>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Deck deck) {
                        deckId = deck.getDeckId();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Utils.showToast(getApplicationContext(),"Nie pobrano kart");
                    }

                    @Override
                    public void onComplete() {
                        Utils.showToast(getApplicationContext(), "Pobrano karty");
                    }
                });
    }

    public void fetchCardFromDeckID(final String deckId, final int count) {
        cardApi.fetchCardFromDeckID(deckId, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Card>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Card card) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    Utils.showToast(getApplicationContext(),"Nie moge pociagnac karty");
                    }

                    @Override
                    public void onComplete() {
                        Utils.showToast(getApplicationContext(), "Pociagnieto karte");
                    }
                });
    }

}

