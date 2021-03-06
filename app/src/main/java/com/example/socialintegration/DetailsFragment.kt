package com.example.socialintegration

//By : Piyush Dnyanadeo Sonawane (Mobile app development Intern @The Sparks Foundation)

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.socialintegration.databinding.FragmentDetailsBinding
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            LoginManager.getInstance().logOut();
            val action = DetailsFragmentDirections.actionDetailsFragmentToLoginFragment()
            view.findNavController().navigate(action)

        }

        val username = firebaseAuth.currentUser?.displayName
        binding.userName.text = username

        val email = firebaseAuth.currentUser?.email
        binding.email.text = email

        //for loading the profile image of the user
        Glide.with(requireContext()).load(firebaseAuth.currentUser?.photoUrl).circleCrop()
            .placeholder(R.drawable.placeholder)
            .into(binding.profileImg)


    }
}