/*
 *  Copyright 2024 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.teamle.controller;

import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.viewmodel.LoginViewModel;
import edu.cnm.deepdive.teamle.viewmodel.PermissionsViewModel;
import edu.cnm.deepdive.teamle.viewmodel.PreferencesViewModel;
import edu.cnm.deepdive.teamle.viewmodel.UserViewModel;

/**
 * Demonstrates access to and observation of {@link androidx.lifecycle.LiveData} elements in
 * {@link LoginViewModel}, {@link UserViewModel}, {@link PermissionsViewModel}, and
 * {@link PreferencesViewModel}, as well as acting as a navigation placeholder. This fragment can be
 * used as an example for creating other navigable fragments that access these core viewmodels; it
 * can then be evolved to provide more application-specific utility, or removed/replaced
 * altogether.
 */
@AndroidEntryPoint
public class GameFragment extends Fragment {



}
