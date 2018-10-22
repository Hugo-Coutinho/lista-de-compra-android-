package com.example.administradorlocal.listadecompras.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.controller.CtrlFragment;
import com.example.administradorlocal.listadecompras.controller.IFragment;
import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.util.AlertDialogImpl;
import com.example.administradorlocal.listadecompras.util.IAlertDialog;
import com.example.administradorlocal.listadecompras.util.ImageManipulate;
import com.example.administradorlocal.listadecompras.util.ImageManipulateImpl;
import com.example.administradorlocal.listadecompras.util.Validate;
import com.example.administradorlocal.listadecompras.util.ValidateImpl;
import com.example.administradorlocal.listadecompras.views.shopList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private Validate validate;

    private static final int REQUEST_TAKE_IMAGE = 1;
    private static String imagePath = "";
    private ImageView ivMyPicture;

    private Bitmap img;
    private Button btnSalvar;
    private ImageButton btnSearchImage;
    private EditText EditProductName;
    private Product p;

    ImageManipulate imageManipulate;
    Context mBase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private IFragment ctrlFragment;
    private IAlertDialog alertDialog;

    public AddProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProductsFragment newInstance(String param1, String param2) {
        AddProductsFragment fragment = new AddProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    //place to build fragment interface.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        p = new Product();
        this.alertDialog = new AlertDialogImpl();
        this.ctrlFragment = new CtrlFragment();
        this.mBase = this.getContext().getApplicationContext();
        this.imageManipulate = new ImageManipulateImpl();
        this.validate = new ValidateImpl();
        this.view = inflater.inflate(R.layout.fragment_add_products, container, false);

        this.btnSearchImage = this.view.findViewById(R.id.btn_take_picture);
        this.btnSalvar = this.view.findViewById(R.id.btn_save);
        this.EditProductName = this.view.findViewById(R.id.editTxt_product_name);
        this.btnSearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);


            }
        });

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String messageToast = getString(R.string.product_save);

                if (!validate.isImageEmpty(img) && validate.isNamevalid(EditProductName.getText().toString())) {
                    imageManipulate.setImage(mBase, EditProductName.getText().toString(), img);
                    shopList.db.productDao().insertProduct(new Product(EditProductName.getText().toString(),
                            imageManipulate.getImagePath(mBase, EditProductName.getText().toString())));
                    Toast.makeText(getActivity(), messageToast, Toast.LENGTH_SHORT).show();
                    toFragment(new CreateProductsFragment(), 2);
                } else {
                    messageToast = validate.imageNameFailedCheck(EditProductName.getText().toString(), img);
                    alertDialog.alertPositiveButton(messageToast, getContext(), 1, null, getFragmentManager(), ctrlFragment);
                }

            }
        });

        // Inflate the layout for this fragment
        return this.view;
    }


    private void toFragment(Fragment frg, Integer navigation) {
        this.ctrlFragment.goToFragment(frg, this.getFragmentManager(), navigation);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                this.img = (Bitmap) bundle.get("data");

                ImageView imageView = (ImageView) this.view.findViewById(R.id.imageView_product);
                imageView.setImageBitmap(this.img);
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





