package aih.iikrhia.chaweeiikrhia
/* MainActivity is how you recyclerview. It is not in the fragment.


 private lateinit var appBarConfiguration: AppBarConfiguration
/*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        /*binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

/*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/

    private void zatseuxakef() {
        String[] kiihiikef = getResources().getStringArray(R.array.oo);
        String[] skakef = getResources().getStringArray(R.array.jj);

        for (int i = 0; i<kiihiikef.length; i++) {
            thalasakef.add(new Kef(kiihiikef[i], skakef[i]));
        }
    }

    //Kefciikai
        val kefciikai = "--ı ſɭɔʞ ʌ ſɟɹƽ ꞁȷ̀ᴜꞇ"
        var eriiha = 1
        while(tlasakef.getRow(eriiha).getCell(0).stringCellValue == kefciikai) {
            eriiha = eriiha + 1
        }
        for(kala in 1 .. eriiha - 1) {
            kiihiikef = tlasakef.getRow(kala).getCell(2).stringCellValue
            skakef = tlasakef.getRow(kala).getCell(3).stringCellValue
            if(tlasakef.getRow(kala).getCell(7) != null) {
                laarinak = tlasakef.getRow(kala).getCell(7).stringCellValue
                thalasakef.add(Kef(kiihiikef, skakef, laarinak))
            }
            else {
                thalasakef.add(Kef(kiihiikef, skakef))
            }
        }

val araq = adapterPosition
            if (araq != RecyclerView.NO_POSITION) {
                malookwek.sahaktsiinakef(araq)
            }

}*/