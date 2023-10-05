package com.example.projectgithub.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectgithub.UserAdapter
import com.example.projectgithub.data.model.ResponseUserGithub
import com.example.projectgithub.databinding.FragmentFollowsBinding
import com.example.projectgithub.detail.DetailViewModel
import com.example.projectgithub.favorite.FavoriteViewModel
import com.example.projectgithub.utils.Result

class FollowsFragment : Fragment() {
    private val favoriteViewModel by activityViewModels<FavoriteViewModel>()

    private  var binding: FragmentFollowsBinding? = null
    private  val adapter by lazy{
        UserAdapter{
        }
    }
    private val  viewModel by activityViewModels<DetailViewModel>()
    var type = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFollows?.apply{
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = this@FollowsFragment.adapter
        }
        when(type){
            FOLLOWERS ->{
            viewModel.resultFollowersUser.observe(viewLifecycleOwner, this::manageResultFollows)
        }
            FOLLOWING ->{
            viewModel.resultFollowingUser.observe(viewLifecycleOwner, this::manageResultFollows)
        }
    }
}
    private fun manageResultFollows(state: Result) {
        when (state) {
            is Result.Success<*> -> {
                adapter.setData(state.data as MutableList<ResponseUserGithub.Item>)
            }

            is Result.Error -> {
                Toast.makeText(requireActivity(), state.exception.message.toString(), Toast.LENGTH_SHORT).show()
            }

            is Result.Loading -> {
                binding?.progressBar?.isVisible = state.isLoading
            }
        }
    }
        companion object {
            const val FOLLOWING = 100
            const val FOLLOWERS = 101
            fun newInstance(type: Int) = FollowsFragment()
                .apply {
                    this.type = type
                }
        }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
