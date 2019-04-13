package com.utopple.code.klondike.oldCode;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.utopple.code.klondike.Card;
import com.utopple.code.klondike.CardLayout;
import com.utopple.code.klondike.CardTapLayout;
import com.utopple.code.klondike.Deck;
import com.utopple.code.klondike.MainActivity;
import com.utopple.code.klondike.R;

import java.util.Iterator;


public class TableDrawer {
	private static int viewWidth;		// Width of screen (px)
	private static int viewHeight;		// Height of screen (px)
	public static int widthOfCard = 50;		// Width of each card when drawn (in px)
	public static int heightOfCard = 75;	// Height of each card when drawn (in px)
	private static int margin;				// Space between card columns (in px)

	public static CardLayout moveCard;	// which card we are trying to move (null when none is selected)

	private MainActivity context;
	private TalonArea talonArea;
	private WasteArea wasteArea;
	private TableauArea[] tableauAreas;
	private FoundationArea[] foundationAreas;

	TableDrawer(MainActivity context){
		// Sizing	--------------------------------------------------------------------------------
		viewWidth = context.getResources().getDisplayMetrics().widthPixels;
		viewHeight = context.getResources().getDisplayMetrics().heightPixels;

		widthOfCard = viewWidth/8;
		heightOfCard = ((int) (widthOfCard * 1.5));

		margin = ((viewWidth-7*widthOfCard)-dpToPx(context,20))/(7);	// Space between cards

		// Initializing	----------------------------------------------------------------------------
		this.context = context;
		talonArea = new TalonArea(context);
		wasteArea = new WasteArea(context);
		tableauAreas = new TableauArea[7];
		for(int i=0; i<tableauAreas.length; i++){
			tableauAreas[i] = new TableauArea(context);
		}
		foundationAreas = new FoundationArea[7];
		for(int i=0; i<foundationAreas.length; i++){
			foundationAreas[i] = new FoundationArea(context);
		}


		// Create CardTapLayouts from deck	--------------------------------------------------------
		Deck deck = new Deck();
		deck.shuffle();

		Iterator<Card> iter;
		CardTapLayout currentCardTapLayout;

		for(int i=0; i<52; i++){
			currentCardTapLayout = new CardTapLayout(context);
			currentCardTapLayout.setCard(deck.getAllCards()[i]);
		}

		for(int i=0; i<7; i++){
			for(int j=0; j<=i; j++){
			///	tableauAreas[i].
			}
		}





		// Positioning on screen	----------------------------------------------------------------

		RelativeLayout.LayoutParams layoutParams;

		((RelativeLayout) context.findViewById(R.id.loc_talon)).addView(talonArea);
		((RelativeLayout) context.findViewById(R.id.loc_waste)).addView(wasteArea);

		((RelativeLayout) context.findViewById(R.id.loc_tableaus)).addView(tableauAreas[0]);

		for(int i=1; i<7; i++){
			((RelativeLayout) context.findViewById(R.id.loc_tableaus)).addView(tableauAreas[i]);


			layoutParams = (RelativeLayout.LayoutParams) tableauAreas[i].getLayoutParams();
			layoutParams.addRule(RelativeLayout.RIGHT_OF, tableauAreas[i-1].getId());
			layoutParams.leftMargin = margin;
			tableauAreas[i].setLayoutParams(layoutParams);
		}

		((RelativeLayout) context.findViewById(R.id.loc_foundations)).addView(foundationAreas[0]);

		for(int i=1; i<4; i++){
			((RelativeLayout) context.findViewById(R.id.loc_foundations)).addView(foundationAreas[i]);


			layoutParams = (RelativeLayout.LayoutParams) foundationAreas[i].getLayoutParams();
			layoutParams.addRule(RelativeLayout.RIGHT_OF, foundationAreas[i-1].getId());
			layoutParams.leftMargin = margin;
			foundationAreas[i].setLayoutParams(layoutParams);
		}


		// Draw cards	----------------------------------------------------------------------------
		talonArea.updateDraw();
		wasteArea.updateDraw();
		for(int i=0; i<7; i++){
			tableauAreas[i].updateDraw();
		}
		for(int i=0; i<4; i++){
			foundationAreas[i].updateDraw();
		}


		// Clickable Areas added onto screen	----------------------------------------------------
		// TALON AREA
		TalonClickableArea talonClickableArea;
		talonClickableArea = new TalonClickableArea(context);

		talonClickableArea.initializeClickable(this);

		layoutParams = new RelativeLayout.LayoutParams(widthOfCard, heightOfCard);
		layoutParams.addRule(RelativeLayout.ALIGN_START, talonArea.getId());
		layoutParams.addRule(RelativeLayout.ALIGN_TOP, talonArea.getId());

		talonClickableArea.setLayoutParams(layoutParams);

		((RelativeLayout)context.findViewById(R.id.loc_talon)).addView(talonClickableArea);

		// WASTE AREA
		WasteClickableArea wasteClickableArea;
		wasteClickableArea = new WasteClickableArea(context);

		wasteClickableArea.initializeClickable(this);

		layoutParams = new RelativeLayout.LayoutParams(widthOfCard, heightOfCard);
		layoutParams.addRule(RelativeLayout.ALIGN_START, wasteArea.getId());
		layoutParams.addRule(RelativeLayout.ALIGN_TOP, wasteArea.getId());

		wasteClickableArea.setLayoutParams(layoutParams);

		((RelativeLayout)context.findViewById(R.id.loc_waste)).addView(wasteClickableArea);

		// FOUNDATION AREAs
		FoundationsClickableArea foundationsClickableArea;

		foundationsClickableArea = new FoundationsClickableArea(context);
		foundationsClickableArea.initializeClickable(this, 0);

		for(int i=0; i<4; i++){
			foundationsClickableArea = new FoundationsClickableArea(context);
			foundationsClickableArea.initializeClickable(this, i);

			layoutParams = new RelativeLayout.LayoutParams(widthOfCard, heightOfCard);
			layoutParams.addRule(RelativeLayout.ALIGN_START, foundationAreas[i].getId());
			layoutParams.addRule(RelativeLayout.ALIGN_TOP, foundationAreas[i].getId());
			foundationsClickableArea.setLayoutParams(layoutParams);
			((RelativeLayout)context.findViewById(R.id.loc_foundations)).addView(foundationsClickableArea);
		}





		//updateClickAreas();


	}


	public void updateClickAreas(){



		wasteArea.updateDraw();
		for(int i=0; i<7; i++){
			tableauAreas[i].updateDraw();
		}
		for(int i=0; i<4; i++){
			foundationAreas[i].updateDraw();
		}



		if(table.checkWin()){
			Toast.makeText(context, "You win!", Toast.LENGTH_SHORT).show();
		}
	}

	public Table getTable(){
		return table;
	}
	public TalonArea getTalonArea() {
		return talonArea;
	}
	public WasteArea getWasteArea() {
		return wasteArea;
	}
	public TableauArea[] getTableauAreas() {
		return tableauAreas;
	}
	public FoundationArea[] getFoundationAreas() {
		return foundationAreas;
	}

	private int dpToPx(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}
}