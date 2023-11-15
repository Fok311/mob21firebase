package com.fok.mob21firebase.ui.tabContainer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fok.mob21firebase.R
import com.fok.mob21firebase.databinding.FragmentTabContainerBinding
import com.fok.mob21firebase.ui.adapter.FragmentAdapter
import com.fok.mob21firebase.ui.home.HomeFragment
import com.fok.mob21firebase.ui.profile.ProfileFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabContainerFragment : Fragment() {
    private lateinit var binding: FragmentTabContainerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpContainer.adapter = FragmentAdapter(
            this,
            listOf(HomeFragment(), ProfileFragment())
        )

        TabLayoutMediator(binding.tlTabs, binding.vpContainer) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Home"
                }
                else -> {
                    tab.text = "Profile"
                }
            }
        }.attach()
    }
}