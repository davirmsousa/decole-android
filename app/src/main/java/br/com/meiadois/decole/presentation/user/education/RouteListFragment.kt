package br.com.meiadois.decole.presentation.user.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.meiadois.decole.R
import br.com.meiadois.decole.presentation.user.education.binding.RouteItem
import br.com.meiadois.decole.presentation.user.education.viewmodel.RouteListViewModel
import br.com.meiadois.decole.presentation.user.education.viewmodel.RouteListViewModelFactory
import br.com.meiadois.decole.util.Coroutines
import br.com.meiadois.decole.util.extension.toRouteItemList
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_route_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RouteListFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factoryFragment: RouteListViewModelFactory by instance()

    private lateinit var mFragmentViewModel: RouteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFragmentViewModel =
            ViewModelProvider(this, factoryFragment).get(RouteListViewModel::class.java)
        bindUi()

    }

    private fun bindUi() = Coroutines.main {
        progress_bar.visibility = View.VISIBLE
        mFragmentViewModel.routes.await().observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = View.GONE
            initRecyclerView(it.toRouteItemList())
        })
    }

    private fun initRecyclerView(routeItem: List<RouteItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(routeItem)

            setOnItemClickListener { item, view ->
                if (item is RouteItem) {
                    mFragmentViewModel.onItemClick(item.route, view)
                }
            }
        }

        route_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

}