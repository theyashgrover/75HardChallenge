package com.example.a75hardchallenge.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.a75hardchallenge.R
import com.example.a75hardchallenge.databinding.FragmentBrowserBinding

class Browser:Fragment(R.layout.fragment_browser) {
    lateinit var webView: WebView
    private val URL="https://www.bodybuilding.com/fun/whats-new.html"
    private lateinit var binding: FragmentBrowserBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBrowserBinding.inflate(layoutInflater,container,false)
        val view=binding.root
        webView=binding.inAppBrowser
        webView.apply {
            loadUrl(URL)
        }

        return view


    }

    }
