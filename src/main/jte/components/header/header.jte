@param String currentPath = "/"

<header id="landing-header" class="sticky top-0 z-50 border-b border-gray-200 bg-white/80 backdrop-blur-sm">
    <!-- Full width container with comfortable padding -->
    <div class="w-full px-8 sm:px-12 lg:px-16">
        <div class="flex h-16 items-center justify-between">

            <!-- Left: Logo -->
            <a href="/" class="flex cursor-pointer items-center gap-3">
                <div class="flex h-10 w-10 items-center justify-center rounded-full bg-[#1e1e1e]">
                    <span class="text-sm font-semibold text-white">Oway</span>
                </div>
                <span class="text-xl font-semibold text-gray-900">Oway Shipping</span>
            </a>

            <!-- Right: Buttons or User Menu -->
            <div class="flex items-center gap-4">

                <!-- User Dropdown Menu (Hover-based) -->
                <div class="relative group">
                    <div class="ml-2 flex h-8 w-8 cursor-pointer items-center justify-center rounded-full bg-[#635bff] transition-colors group-hover:bg-[#534bda]">
                        <span class="text-sm font-medium text-white">
                            ?
                        </span>
                    </div>

                    <!-- Dropdown Menu (shows on hover) -->
                    <div class="invisible opacity-0 group-hover:visible group-hover:opacity-100 transition-all duration-200 absolute right-0 mt-2 w-64 bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5">

                        <div class="py-1">
                            <a href="/profile"
                               class="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                <svg class="mr-2 h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/>
                                </svg>
                                Profile
                            </a>
                            <a href="/settings"
                               class="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                <svg class="mr-2 h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z"/>
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                                </svg>
                                Settings
                            </a>
                            <a href="/security"
                               class="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                <svg class="mr-2 h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                                </svg>
                                Security
                            </a>
                            <hr class="my-1 border-gray-200">
                            <a href="/billing"
                               class="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                <svg class="mr-2 h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"/>
                                </svg>
                                Billing
                            </a>
                            <form action="/logout" method="post" class="block">
                                <button type="submit"
                                        class="flex w-full items-center px-4 py-2 text-sm text-red-600 hover:bg-gray-100 text-left">
                                    Logout
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>