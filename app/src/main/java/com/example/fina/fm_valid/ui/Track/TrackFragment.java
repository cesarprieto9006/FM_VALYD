package com.example.fina.fm_valid.ui.Track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fina.fm_valid.R;
import com.example.fina.fm_valid.adapter.ArtistAdapter;
import com.example.fina.fm_valid.adapter.TrackAdapter;
import com.example.fina.fm_valid.adapter.TrackSAdapter;
import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_SearchT_Response;
import com.example.fina.fm_valid.model.Data_Track;
import com.example.fina.fm_valid.model.Data_Track_Response;
import com.example.fina.fm_valid.model.Data_Track_S;
import com.example.fina.fm_valid.sqlite.SQLiteController;
import com.example.fina.fm_valid.utils.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class TrackFragment extends Fragment implements TrackViewInterface {

    @BindView(R.id.rvArtist)
    RecyclerView rvArtist;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.btnBack)
    ImageButton btnBack;

    @BindView(R.id.btnNext)
    ImageButton btnNext;

    @BindView(R.id.lblPage)
    TextView lblPage;

    @BindView(R.id.lblTotalPages)
    TextView lblTotalPage;

    @BindView(R.id.txtSearch)
    TextView txtSearch;

    @BindView(R.id.lyPage)
    LinearLayout lyPage;

    private View root;
    private TrackPresenter trackPresenter;
    private TrackAdapter trackAdapter;
    private TrackSAdapter tracksAdapter;
    private int PageF = 1;
    private String TextS = "";
    private SQLiteController sqLiteController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_track, container, false);

        setupView();
        setupMVP();
        track();

        return root;
    }

    private void setupView() {
        ButterKnife.bind(this, root);
        sqLiteController = new SQLiteController(getContext());
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext()));
        rvArtist.setHasFixedSize(true);
        rvArtist.setItemAnimator(new DefaultItemAnimator());
    }

    private void setupMVP() {
        trackPresenter = new TrackPresenter(this);
    }

    private void track() {
        trackPresenter.getTrack(Constant.Country, Constant.Key, Constant.Limit, Constant.Page);
    }

    @OnClick(R.id.btnBack)
    void onClickedBack() {
        int PageI = Integer.valueOf(lblPage.getText().toString());
        if (PageI > 0 && PageI <= PageF && PageI != 1 && TextS.equals(""))
            trackPresenter.getTrack(Constant.Country, Constant.Key, Constant.Limit, PageI - 1);
        else if (PageI > 0 && PageI <= PageF && PageI != 1 && !TextS.equals(""))
            trackPresenter.getSearchArtist(TextS, Constant.Key, Constant.Limit, PageI - 1);
        else
            displayError(getString(R.string.error_main_page));
    }

    @OnClick(R.id.btnNext)
    void onClickedNext() {
        int PageI = Integer.valueOf(lblPage.getText().toString());
        if (PageI > 0 && PageI < PageF && PageI != PageF && TextS.equals(""))
            trackPresenter.getTrack(Constant.Country, Constant.Key, Constant.Limit, PageI + 1);
        else if (PageI > 0 && PageI < PageF && PageI != PageF && !TextS.equals(""))
            trackPresenter.getSearchArtist(TextS, Constant.Key, Constant.Limit, PageI + 1);
        else
            displayError(getString(R.string.error_end_page));
    }

    @OnEditorAction(R.id.txtSearch)
    boolean onEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (txtSearch.getText().toString().equals("")) {
                TextS = "";
                trackPresenter.getTrack(Constant.Country, Constant.Key, Constant.Limit, Constant.Page);
            } else {
                TextS = txtSearch.getText().toString();
                int PageI = Integer.valueOf(lblPage.getText().toString());
                trackPresenter.getSearchArtist(TextS, Constant.Key, Constant.Limit, Constant.Page);
            }
            return true;
        }
        return false;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLyPage() {
        lyPage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLyPage() {
        lyPage.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        rvArtist.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        rvArtist.setVisibility(View.GONE);
    }

    @Override
    public void updatePageTrack(Data_Track_Response dataResponse) {
        if (dataResponse.getTracks().getAttr() != null) {
            PageF = Integer.valueOf(dataResponse.getTracks().getAttr().getTotalPages());
            lblPage.setText(dataResponse.getTracks().getAttr().getPage());

            if (PageF == 0)
                lblTotalPage.setText(" " + 1);
            else
                lblTotalPage.setText(" " + PageF);
        }
    }

    @Override
    public void updatePageSearch(Data_SearchT_Response dataResponse) {
        if (dataResponse.getResults().getQuery() != null) {
            PageF = Integer.valueOf(dataResponse.getResults().getTotalResults()) / Integer.valueOf(dataResponse.getResults().getItemsPerPage());
            lblPage.setText(dataResponse.getResults().getQuery().getStartPage());

            if (PageF == 0)
                lblTotalPage.setText(" " + 1);
            else
                lblTotalPage.setText(" " + PageF);
        }
    }

    @Override
    public void displayError(String Mensaje) {
        Toast.makeText(getContext(), Mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stateTrack(ArrayList<Data_Track> dataResponse) {
        sqLiteController.Delete_Track();
        for (Data_Track dataTrack : dataResponse) {
            sqLiteController.Save_Track(dataTrack);
        }


        trackAdapter = new TrackAdapter(dataResponse, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvArtist.setLayoutManager(layoutManager);
        rvArtist.setAdapter(trackAdapter);

    }

    @Override
    public void stateTrackS(ArrayList<Data_Track_S> dataResponse) {
        sqLiteController.Delete_Track();
        for(Data_Track_S dataArtist:dataResponse) {
            //sqLiteController.
            sqLiteController.Save_Track_Search(dataArtist);
        }
        tracksAdapter = new TrackSAdapter(dataResponse, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvArtist.setLayoutManager(layoutManager);
        rvArtist.setAdapter(tracksAdapter);
    }


    @Override
    public void getDatabase(String text) {

        if (text.equals("")) {
            ArrayList<Data_Track> dataResponse = new ArrayList<>();
            dataResponse = sqLiteController.getTrackDetail();
            trackAdapter = new TrackAdapter(dataResponse, getContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvArtist.setLayoutManager(layoutManager);
            rvArtist.setAdapter(trackAdapter);
        } else {
            ArrayList<Data_Track_S> dataResponse = new ArrayList<>();
            dataResponse = sqLiteController.getTrackSearchDetail(text);
            tracksAdapter = new TrackSAdapter(dataResponse, getContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvArtist.setLayoutManager(layoutManager);
            rvArtist.setAdapter(tracksAdapter);
        }




    }
}