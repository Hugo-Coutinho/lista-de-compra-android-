package com.example.administradorlocal.listadecompras.feature.createProducts.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.feature.createProducts.adapter.ProductListAdapter;
import com.example.administradorlocal.listadecompras.feature.HomeShoppingList.fragment.HomeShoppingListFragment;
import com.example.administradorlocal.listadecompras.util.fragmentHelper.CtrlFragment;
import com.example.administradorlocal.listadecompras.util.fragmentHelper.IFragment;
import com.example.administradorlocal.listadecompras.persistence.entity.Product;
import com.example.administradorlocal.listadecompras.persistence.entity.ShoppingList;
import com.example.administradorlocal.listadecompras.util.alertDialog.AlertDialogImpl;
import com.example.administradorlocal.listadecompras.util.alertDialog.IAlertDialog;
import com.example.administradorlocal.listadecompras.feature.main.main_container;
import com.example.administradorlocal.listadecompras.util.toolbar.CreateProductToolbar;

import java.util.Date;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProductsFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Product> productList;
    private View view;
    private ListView lv;
    private View includeListEmpty;
    private View includeList;
    private ProductListAdapter productListAdapter;
    private IFragment ctrlFragment;
    private IAlertDialog alertDialog;

    public CreateProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateProductsFragment newInstance(String param1, String param2) {
        CreateProductsFragment fragment = new CreateProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.setupFragment(inflater, container);
        this.configureToolbar();
        lv.setAdapter(productListAdapter);
        // Inflate the layout for this fragment
        return this.view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.startHomeVisibility();
    }

    private void setupFragment(LayoutInflater inflater, ViewGroup container) {
        this.view = inflater.inflate(R.layout.fragment_create_products, container, false);
        this.includeList = this.view.findViewById(R.id.include_listview);
        this.includeListEmpty = this.view.findViewById(R.id.include_emptylist);
        this.ctrlFragment = new CtrlFragment();
        this.alertDialog = new AlertDialogImpl();
        this.productList = main_container.db.productDao().findAll();
        this.lv = this.view.findViewById(R.id.lv_showListProduct);
        this.productListAdapter = new ProductListAdapter(productList, this.getContext());
    }

    private void startHomeVisibility() {
        if (this.productList.size() == 0) {
            this.setEmptyListVisibility();
            return;
        }
        this.setListVisibility();
    }

    private void setListVisibility() {
        includeList.setVisibility(View.VISIBLE);
        includeListEmpty.setVisibility(View.GONE);
    }

    private void setEmptyListVisibility() {
        includeList.setVisibility(View.GONE);
        includeListEmpty.setVisibility(View.VISIBLE);
    }

    private void configureToolbar() {
        final CreateProductToolbar createProductToolbar  = new CreateProductToolbar(this.view,this.getContext());
        createProductToolbar.configureTitle();
        ((AppCompatActivity) getActivity()).setSupportActionBar(createProductToolbar.getToolbar());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        List<Product> products = productListAdapter.getProductsChecked();

        switch (item.getItemId()) {

            case R.id.i_createList:
                // Do onlick on menu action here
                if (products.size() > 0) {
                    createList(products);
                    ctrlFragment.goToFragment(new HomeShoppingListFragment(), this.getFragmentManager(), 0);
                    Toast.makeText(getContext(), "lista criada com sucesso!!", Toast.LENGTH_LONG).show();
                } else {
                    this.alertDialog.alertPositiveButton("para criar a lista selecione os produtos", getContext(), 2, new CreateProductsFragment(), getFragmentManager(), ctrlFragment);
                }
                return true;

            case R.id.i_delete_Products:

                if (products.size() > 0) {
                    deleteAllProducts(products);
                    ctrlFragment.goToFragment(new CreateProductsFragment(), this.getFragmentManager(), 2);
                    Toast.makeText(getContext(), "deletado com sucesso!!", Toast.LENGTH_LONG).show();
                } else {
                    this.alertDialog.alertPositiveButton("para deletar produtos os selecionem", getContext(), 2, new CreateProductsFragment(), getFragmentManager(), ctrlFragment);
                }
                return true;
        }
        return false;
    }

    private void deleteList() {
        main_container.db.ShoppingListDao().deleteAll();
    }

    private void createList(List<Product> p) {
        deleteList();
        for (Product pr : p) {
            main_container.db.ShoppingListDao().insertListProduct(new ShoppingList(pr.getName(), pr.getImage(), new Date().getTime()));
        }

    }

    private void deleteAllProducts(List<Product> p) {
        List<ShoppingList> shop = main_container.db.ShoppingListDao().findAll();

        for (Product x : p) {
            main_container.db.productDao().delete(x);
            for (ShoppingList s : shop) {
                if (s.getName().equals(x.getName())) {
                    main_container.db.ShoppingListDao().delete(s);
                }
            }
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
