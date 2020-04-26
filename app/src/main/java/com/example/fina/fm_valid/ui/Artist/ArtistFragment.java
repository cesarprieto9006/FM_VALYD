package com.example.fina.fm_valid.ui.Artist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fina.fm_valid.R;
import com.example.fina.fm_valid.adapter.TransaccionAdapter;
import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_Search_Response;
import com.example.fina.fm_valid.utils.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;


public class ArtistFragment extends Fragment implements ArtistViewInterface{

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


    private View root;
    private ArtistPresenter artistPresentert;
    private TransaccionAdapter transaccionAdapter;
    private int PageF=1;
    private String TextS="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_artist, container, false);

        setupView();
        setupMVP();
        artist();

        return root;
    }

    private void setupView() {
        ButterKnife.bind(this, root);
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext()));
        rvArtist.setHasFixedSize(true);
        rvArtist.setItemAnimator(new DefaultItemAnimator());
    }

    private void setupMVP() {
        artistPresentert = new ArtistPresenter(this);
    }

    private void artist() {
        artistPresentert.getArtist(Constant.Country,Constant.Key,Constant.Limit,Constant.Page);
    }

    @OnClick(R.id.btnBack)
    void onClickedBack() {
        int PageI=Integer.valueOf(lblPage.getText().toString());
        if(PageI>0 &&  PageI<=PageF && PageI!=1 && TextS.equals(""))
            artistPresentert.getArtist(Constant.Country,Constant.Key,Constant.Limit,PageI-1);
        else if(PageI>0 &&  PageI<=PageF && PageI!=1 && !TextS.equals(""))
            artistPresentert.getSearchArtist(TextS,Constant.Key,Constant.Limit,PageI-1);
        else
            displayError(getString(R.string.error_main_page));
    }

    @OnClick(R.id.btnNext)
    void onClickedNext() {
        int PageI=Integer.valueOf(lblPage.getText().toString());
        if(PageI>0 &&  PageI<PageF && PageI!=PageF && TextS.equals(""))
            artistPresentert.getArtist(Constant.Country,Constant.Key,Constant.Limit,PageI+1);
        else if (PageI>0 &&  PageI<PageF && PageI!=PageF && !TextS.equals(""))
            artistPresentert.getSearchArtist(TextS,Constant.Key,Constant.Limit,PageI+1);
        else
            displayError(getString(R.string.error_end_page));
    }

    @OnEditorAction(R.id.txtSearch)
    boolean onEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (txtSearch.getText().toString().equals("")) {
                TextS="";
                artistPresentert.getArtist(Constant.Country,Constant.Key,Constant.Limit,Constant.Page);
            } else {
                TextS=txtSearch.getText().toString();
                int PageI=Integer.valueOf(lblPage.getText().toString());
                artistPresentert.getSearchArtist(TextS,Constant.Key,Constant.Limit,Constant.Page);
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
    public void showList() {
        rvArtist.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        rvArtist.setVisibility(View.GONE);
    }

    @Override
    public void updatePageArtist(Data_Artist_Response dataResponse) {
        if(dataResponse.getTopartists().getAttr()!=null) {
            PageF=Integer.valueOf(dataResponse.getTopartists().getAttr().getTotalPages());
            lblPage.setText(dataResponse.getTopartists().getAttr().getPage());

            if(PageF==0)
                lblTotalPage.setText(" "+1);
            else
                lblTotalPage.setText(" "+PageF);
        }
    }

    @Override
    public void updatePageSearch(Data_Search_Response dataResponse) {
        if(dataResponse.getResults().getQuery()!=null) {
            PageF=Integer.valueOf(dataResponse.getResults().getTotalResults())/Integer.valueOf(dataResponse.getResults().getItemsPerPage());
            lblPage.setText(dataResponse.getResults().getQuery().getStartPage());

            if(PageF==0)
                lblTotalPage.setText(" "+1);
            else
                lblTotalPage.setText(" "+PageF);
        }
    }

    @Override
    public void displayError(String Mensaje) {
        Toast.makeText(getContext(), Mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stateArtist(ArrayList<Data_Artist> dataResponse) {
        if (transaccionAdapter == null) {
            transaccionAdapter = new TransaccionAdapter(dataResponse, getContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvArtist.setLayoutManager(layoutManager);
            rvArtist.setAdapter(transaccionAdapter);
        }else {
            transaccionAdapter.setData(dataResponse);
        }

    }

    @Override
    public boolean validate() {
        return false;
    }
}