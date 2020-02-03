package com.hiroozawa.ractsil.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.hiroozawa.ractsil.databinding.FragmentCarDetailBinding
import com.hiroozawa.ractsil.di.DaggerBottomSheetFragment
import javax.inject.Inject


class CarDetailDialogFragment : DaggerBottomSheetFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CarDetailViewModel by viewModels { viewModelFactory }
    private val args: CarDetailDialogFragmentArgs by navArgs()

    private lateinit var viewDataBinding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCarDetailBinding.inflate(layoutInflater, container, false)

        viewDataBinding.viewmodel = viewModel

        viewModel.load(args.carId)

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }
}